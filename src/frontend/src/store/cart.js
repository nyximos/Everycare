export default {
    namespaced: true,
    state: {
        productId: '',
        amount: '',
        quantity: '',
        cart:[]
    },
    mutations: {
        //장바구니에 제품을 추가
        pushProductToCart(state, product){
            state.cart.push({
                productId: product.id,
                amount: product.amount,
                quantity: product.quantity
            });
        },
        incrementItemQuantity(state,item){
            state.cart.quantity=item.quantity++;
        },
        minus(state, item){
            state.cart.quantity=item.quantity--;
        },
        remoteList(state, index){
            console.log("도착")
            state.cart.splice(index,1);
        },
        removeAll(state){       
               state.cart=[]
        },
    },
};