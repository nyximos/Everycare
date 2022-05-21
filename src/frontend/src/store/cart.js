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
            const cartItems = state.cart.filter(cartItem => cartItem.id === product.id);
            if (cartItems.length === 0) {
                state.cart.push({
                    productId: product.id,
                    amount: product.amount,
                    quantity: product.quantity
                });
            } else {
                cartItems[0].quantity++
            }
            
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