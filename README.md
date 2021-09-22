# UsefulPlugin
서버에 도움 될것 같은 플러그인 (초보작)
각별님의 kommand를 사용했습니다.

### 1.1 업데이트 내역
- `maxHomeLetter` -> `maxHomeLength`
- back 설정 추가
- 이제 back 명령어에 딜레이를 줄 수 있습니다.
- 좌클릭시 블록이 바로 사라져버리는 현상이 수정되었습니다.

## 기본 명령어

### gm
기존 게임모드 명령어를 단축시킨 명령어입니다.
* 사용 방법
  - `gm <int>` : 자신의 게임모드를 `<int>`로 변경합니다 (0~3)
  - `gm <int> <player>` : `<player>`의 게임모드를 `<int>`로 변경합니다

## 백 명령어

## back
죽은 자리로 되돌아가는 명령어입니다.
* 사용 방법
  - `back` : 죽은 자리로 되돌아갑니다.

### backsetting
백 명령어에 대한 설정 명령어
* 사용 방법
  - `backsetting <path>` : `<path>` 설정을 확인합니다.
  - `backsetting <path> <value>` : `<path>` 설정을 `<value>`로 설정합니다.
* path 목록
  - `delayTime` : 명령어 쿨타임


## 홈 명령어

### sethome
셋홈 명령어
* 사용 방법
  - `sethome` : 기본값의 이름을 가진 홈을 생성합니다.
  - `sethome <name>` : `<name>` 이름을 가진 홈을 생성합니다.

### home
홈 이동 명령어
* 사용 방법
  - `home` : 기본값의 이름을 가진 홈으로 이동합니다
  - `home <name>` : `<name>` 이름을 가진 홈으로 이동합니다.

### removehome / deletehome
홈 삭제 명령어
* 사용 방법
  - `removehome` : 기본값의 이름을 가진 홈을 삭제합니다.
  - `removehome <name>` : `<name>` 이름을 가진 홈을 삭제합니다.

### homegui
홈 GUI를 오픈합니다.
* 사용 방법
  - `homegui` : 홈 GUI를 오픈합니다.
  
### homesetting
홈 설정 명령어
* 사용 방법
  - `homesetting <path>` : `<path>` 설정을 확인합니다.
  - `homesetting <path> <value>` : `<path>` 설정을 `<value>`로 설정합니다.
* path 목록
  - `defaultHomeName` : 기본 홈 이름
  - `maxNameLength` : 홈 이름 길이
  - `maxHomeCount` : 최대 홈 개수
