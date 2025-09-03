import os
import re
import json
import requests
from collections import defaultdict
from urllib.parse import quote

# --- 설정 ---
BAEKJOON_DIR = '백준'
README_FILE = 'README.md'
PLACEHOLDER_START = '<!-- BAEKJOON:START -->'
PLACEHOLDER_END = '<!-- BAEKJOON:END -->'
BAEKJOON_URL = 'https://www.acmicpc.net/problem'
SOLVED_AC_API_URL = 'https://solved.ac/api/v3/problem/lookup'
# GitHub 사용자 이름과 저장소 이름을 설정해주세요.
GITHUB_USER = 'meraki6512'
GITHUB_REPO = 'Algorithm25'

def level_to_tier(level):
    """solved.ac 레벨을 'Gold II'와 같은 문자열 티어로 변환합니다."""
    if level == 0: return "Unrated"
    tiers = ["Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"]
    roman = ["V", "IV", "III", "II", "I"]
    tier_idx = (level - 1) // 5
    sub_idx = (level - 1) % 5
    return f"{tiers[tier_idx]} {roman[sub_idx]}"

def get_problem_metadata(problem_ids):
    """solved.ac API를 사용해 여러 문제의 메타데이터를 일괄 조회합니다."""
    if not problem_ids:
        return {}

    print(f"Fetching metadata for {len(problem_ids)} problems from solved.ac...")
    try:
        problem_metadata = {}
        # API는 한 번에 100개까지 조회가 가능하므로 100개씩 나눠서 요청
        for i in range(0, len(problem_ids), 100):
            chunk = problem_ids[i:i+100]
            params = {'problemIds': ','.join(map(str, chunk))}
            response = requests.get(SOLVED_AC_API_URL, params=params)
            response.raise_for_status() # HTTP 오류 발생 시 예외 throw

            for problem in response.json():
                problem_metadata[problem['problemId']] = {
                    'level': problem['level'],
                    'title': problem['titleKo']
                }
        print("✅ Metadata fetched successfully.")
        return problem_metadata
    except requests.exceptions.RequestException as e:
        print(f"❌ Failed to fetch data from solved.ac API: {e}")
        return {}

def get_solved_problems():
    """파일 시스템을 스캔하고 solved.ac API로 메타데이터를 보강합니다."""
    problems_by_tier = defaultdict(list)
    problem_ids_to_lookup = []
    local_problems = {}

    if not os.path.exists(BAEKJOON_DIR):
        return {}

    # 1. 파일 시스템에서 문제 목록 수집
    for root, dirs, files in os.walk(BAEKJOON_DIR):
        for filename in files:
            match = re.match(r'(\d+)', filename)
            if match:
                problem_num = int(match.group(1))
                problem_ids_to_lookup.append(problem_num)

                encoded_path = quote(os.path.join(root, filename).replace(os.sep, '/'))
                solution_link = f'https://github.com/{GITHUB_USER}/{GITHUB_REPO}/blob/main/{encoded_path}'
                local_problems[problem_num] = {'solution_link': solution_link}

    # 2. solved.ac API에서 메타데이터 일괄 조회
    metadata = get_problem_metadata(list(set(problem_ids_to_lookup)))

    # 3. 메타데이터와 파일 정보를 결합하여 티어별로 그룹화
    for num, data in local_problems.items():
        if num in metadata:
            tier_name = level_to_tier(metadata[num]['level'])
            problem_name = metadata[num]['title']
            problems_by_tier[tier_name].append({
                'num': num, 'name': problem_name, 'solution_link': data['solution_link']
            })
        else: # API 조회 실패 또는 신규 문제 등
            problems_by_tier["Unrated"].append({
                'num': num, 'name': f'{num}번', 'solution_link': data['solution_link']
            })

    for tier in problems_by_tier:
        problems_by_tier[tier].sort(key=lambda x: x['num'])

    return problems_by_tier

def generate_markdown(problems_by_tier):
    """Markdown 형식의 문자열을 생성합니다."""
    if not problems_by_tier: return "푼 문제가 아직 없습니다."

    # 티어 순서 정의 (Ruby I -> Bronze V)
    tiers = ["Ruby", "Diamond", "Platinum", "Gold", "Silver", "Bronze"]
    roman = ["I", "II", "III", "IV", "V"]
    tier_order = [f"{t} {r}" for t in tiers for r in roman]

    sorted_tiers = sorted(
        problems_by_tier.keys(),
        key=lambda x: tier_order.index(x) if x in tier_order else len(tier_order)
    )
    if "Unrated" in problems_by_tier: sorted_tiers.append("Unrated")

    lines = []
    for tier in sorted_tiers:
        lines.append(f"### {tier}")
        lines.append("| 문제 번호 | 문제 이름 | 풀이 코드 |")
        lines.append("| :---: | :---: | :---: |")
        for p in problems_by_tier[tier]:
            problem_md_link = f"[{p['num']}]({BAEKJOON_URL}/{p['num']})"
            solution_md_link = f"[바로가기]({p['solution_link']})"
            lines.append(f"| {problem_md_link} | {p['name']} | {solution_md_link} |")
        lines.append("")
    return '\n'.join(lines)

def update_readme(markdown_content):
    """README.md 파일의 플레이스홀더 부분을 업데이트합니다."""
    with open(README_FILE, 'r', encoding='utf-8') as f:
        readme_content = f.read()
    new_content = re.sub(
        f'({re.escape(PLACEHOLDER_START)})(.*?)({re.escape(PLACEHOLDER_END)})',
        f'\\1\n\n{markdown_content}\n\\3', readme_content, flags=re.DOTALL
    )
    with open(README_FILE, 'w', encoding='utf-8') as f:
        f.write(new_content)
    print("✅ README.md has been updated successfully.")

if __name__ == "__main__":
    problems = get_solved_problems()
    markdown = generate_markdown(problems)
    update_readme(markdown)

