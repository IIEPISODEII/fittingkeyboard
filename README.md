# fittingkeyboard
This is my first customized android keyboard (ime service) in KR/ENG  
제 첫 안드로이드 커스텀 키보드 어플리케이션입니다. 현재 한글/영어를 지원합니다.

## 프로젝트 구성
Projects consist of 3 main components.  
프로젝트는 크게 3가지 요소로 구성되어있습니다.
1. Keyboard Service
2. Setting Activitiy
3. SharedPreference

After saving each setting datas of Setting Activity on SharedPrefence, Keyboard Service fetch it and apply it with layout or features.
Setting Activity에서 구성한 개별 설정을 SharedPreference에 저장 후, Keyboard Service에서 이를 불러와 레이아웃, 기능 등 키보드 설정에 반영합니다.

![피팅키보드 구조](https://github.com/IIEPISODEII/fittingkeyboard/assets/60639734/1e56d3f5-fff2-434f-a29f-1982c811e007)

## 사용 기술
- Glide
- Databinding (Partially)

## 결과물
![레코딩_Trim_Trim (1)](https://user-images.githubusercontent.com/60639734/152549013-cdad992c-f5bc-4acd-acaa-89494c4ad4f4.gif)
