export default {
    namespaced: true,
    state:{
        title: '',
        caretarget:'',
        schedul:'',
        dates:'',
        day:'',
        sittersex:'',
        paytimepick:'',
        pay:'',
        comment:''
    },
    mutations:{
        set_data(state, findpeople){
            state.title = findpeople.title,
            state.caretarget = findpeople.caretarget,
            state.schedul = findpeople.schedul,
            state.dates =  findpeople.dates,
            state.day = findpeople.day,
            state.sittersex = findpeople.sittersex,
            state.paytimepick = findpeople.paytimepick,
            state.pay = findpeople.pay,
            state.comment = findpeople.comment
        }
    },
    actions: {},
    getters:{}
}