export default {
    namespaced: true,
    state: {
        actid: '',
        targetName:'',
    },
    mutations: {
        onEdit(state, userData) {
            state.actid = userData.actId;
        },
        targetData(state,targetData){
            state.targetName = targetData.targetName
        }
        
    },
};
