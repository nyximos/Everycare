## 프로젝트 세팅 🛠

```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve
```

### Compiles and minifies for production

```
npm run build
```

## 작업 기간 📅

2022-02-7 ~ 2022-06

## 작업

## 의존 라이브러리 📦

-   `Vue CLI v4.5.10`
-   `vuetify`
-   `vuex`
-   `vue-router`
-   `sass`
-   `axios`

## 디렉토리 구조 📂

-   `src/api/core` : axios 인스턴스 내보내는 파일
-   `src/api/repository` : axios 인스턴스를 사용한 api 요청 파일
-   `src/global` : 기본 vue 컴포넌트 폴더
-   `src/global/index.js` : webpack 모듈 시스템을 사용해서 기본 컴포넌트를 Vue 인스턴스에 자동 등록해주는 파일
-   `src/Modules/` : 프로젝트 컴포넌트
-   `src/pages/` : 등록된 router에 의해서 호출되는 vue 컴포넌트
-   `src/plugins/` : plugin 파일
-   `src/router/` : vue-router 설정
-   `src/store`/ : vuex 설정 및 모듈
-   `src/App.vue` : 최상위 컴포넌트
-   `src/main.js` : Vue 인스턴스 설정
