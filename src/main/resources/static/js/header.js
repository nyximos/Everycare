const logout = () => {
    $.ajax({
        url: "/api/token",
        type: 'DELETE',
        cache: false,
        contentType: false,
        processData: false,
        async: false,
    })
    location.href="/"
}
