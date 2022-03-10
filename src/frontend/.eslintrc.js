module.exports = {
	// 현재 eslintrc 파일을 기준으로 ESLint 규칙을 적용
	root: true,
	// 추가적인 규칙들을 적용
	extends: ['eslint:recommended', 'plugin:vue/essential', 'prettier', 'plugin:prettier/recommended'],
	// 코드 정리 플러그인 추가
	plugins: ['prettier'],
	// 사용자 편의 규칙 추가
	rules: {
		'prettier/prettier': [
			'error',
			// 아래 규칙들은 개인 선호에 따라 prettier 문법 적용
			// https://prettier.io/docs/en/options.html
			{
				singleQuote: true,
				semi: true,
				useTabs: true,
				tabWidth: 2,
				trailingComma: 'all',
				printWidth: 200,
				bracketSpacing: true,
				arrowParens: 'avoid',
			},
		],
		'vue/html-self-closing': [
			'error',
			{
				html: {
					void: 'always', // 빈 태그
					normal: 'never', // 일반 태그
					component: 'always',
				},
				svg: 'always',
				math: 'always',
			},
		],
		'vue/html-closing-bracket-newline': [
			// 닫히는 괄호 반드시 다음줄에 명시해야한다는 규칙
			'error',
			{
				singleline: 'never',
				multiline: 'never',
			},
		],
		'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
	},
};
