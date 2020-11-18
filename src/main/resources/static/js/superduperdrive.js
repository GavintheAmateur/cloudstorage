function showNoteModal(note) {
    if (note) {
        $('#note-id').val(note.id ? note.id : 0);
        $('#note-title').val(note.noteTitle ? note.noteTitle : '');
        $('#note-description').val(note.noteDescription ? note.noteDescription : '');

    }
    $('#noteModal').modal('show');
}


// For opening the credentials modal
function showCredentialModal(credential) {
    if (credential) {
        $('#credential-id').val(credential.id ? credential.id : 0);
        $('#credential-url').val(credential.url ? credential.url : '');
        $('#credential-username').val(credential.username ? credential.username : '');
        $('#credential-password').val(credential.plainPassword ? credential.plainPassword : '');
    }
    $('#credentialModal').modal('show');
}

function uploadFile() {
    var e = arguments[0]
    for (a in arguments) {
        console.log("a: "+a)
    }

    var file = $("#fileUpload").val();
    console.log("file: "+file)
    if (file == "") {
        e.preventDefault();
        alert("please make sure to select a file!");
        return false;
    } else {
        return true;
    }
}
