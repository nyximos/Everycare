const state = {
    event: initEvent(),
    events: [],
    dialog: false,
};

function initEvent(){
    return {
        startDate: '',
        startTime: '',
        endDate: '',
        endTime: '',
        content: '',
        title: '',
    }
}