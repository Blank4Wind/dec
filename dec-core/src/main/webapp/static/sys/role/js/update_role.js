$('#infoForm').validator({
    fields: {
        'roleEntity.name': 'required;',
        'roleEntity.memo' : 'length[0~255, true];filter(\'<>/);'
    },
});