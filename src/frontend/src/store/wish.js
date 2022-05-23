export default {
    namespaced: true,
    state: {
        productId: '',
        amount: '',
        quantity: '',
        wish:[]
    },
    mutations: {
        //장바구니에 제품을 추가
        pushProductToWish(state, product){
            state.wish.push({
                productId: product.id,
                amount: product.amount,
                quantity: product.quantity
            });
        console.log(state.wish)
        },
        remoteList(state, index){
            console.log("도착")
            state.wish.splice(index,1);
        },
    },
};