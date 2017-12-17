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
            { "mData": "department.name" },
            { "mData": "status.name" },
            { "mData": "priority.name" },
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
            { "mData": "department.name" },
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

$(document).ready( function () {
    var table = $('#departmentsTable').DataTable({
        "sAjaxSource": "/getAllDepartments",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" }
        ]
    })

    $('#departmentsTable').on('click', 'tbody td', function(){
        window.location.href = '/department/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});