$(document).ready( function () {
    var table = $('#defectsTable').DataTable({
        "sAjaxSource": "/getAllFault",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData":  "faultId"},
            { "mData": "email" },
            { "mData": "description" },
            { "mData": "department.departmentName" },
            { "mData": "status" },
            { "mData": "priority" },
            { "mData": "createDate" }
        ]
    })

    $('#defectsTable').on('click', 'tbody td', function(){
        window.location.href = '/defect/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});

$(document).ready( function () {
    var table = $('#usersTable').DataTable({
        "sAjaxSource": "/getAllUsers",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "userId"},
            { "mData": "email" },
            { "mData": "department.departmentName" },
            { "mData": "roles[0].role" },
            { "mData": "firstName" },
            { "mData": "lastName" },
            { "mData": "phoneNumber" }
        ]
    })

    $('#usersTable').on('click', 'tbody td', function(){
        window.location.href = '/user/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});