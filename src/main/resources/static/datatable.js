$(document).ready( function () {
    if ($(document).attr('title') == 'Usterki') {
        var lang = 'Polish.json';
    } else {
        var lang = 'English.json';
    }
    var table = $('#defectsTable').DataTable({
        language: {
            url: 'http://cdn.datatables.net/plug-ins/1.10.16/i18n/' + lang
        },
        "sAjaxSource": "/getAllFault",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData":  "faultId"},
            { "mData": "email" },
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
        window.location.href = '/defectElement/Department/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});

$(document).ready( function () {
    var table = $('#statusTable').DataTable({
        "sAjaxSource": "/getAllStatuses",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" }
        ]
    })

    $('#statusTable').on('click', 'tbody td', function(){
        window.location.href = '/defectElement/Status/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});

$(document).ready( function () {
    var table = $('#priorityTable').DataTable({
        "sAjaxSource": "/getAllPriorities",
        "sAjaxDataProp": "",
        "stateSave": "true",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" }
        ]
    })

    $('#priorityTable').on('click', 'tbody td', function(){
        window.location.href = '/defectElement/Priority/' + $(this).closest('tr').find('td:eq(0)').html();
    });
});