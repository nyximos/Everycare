const login = () => {
    const username = document.getElementsByName('username')[0].value;
    const password = document.getElementsByName('password')[0].value;
    const json = {
        username: username,
        password: password,
    };
    axios
        .post('/login', json)
        .then((res) => {
            // this.test = response.data;
            console.log(res);
            location.href="/"
        })
        .catch((err) => {
            console.log(err);
        });
};