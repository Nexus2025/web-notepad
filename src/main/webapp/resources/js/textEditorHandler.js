var quill;

function initTextEditor() {

    var element =  document.getElementById('activedContent');

    if (typeof(element) != 'undefined' && element != null) {
        var toolbarOptions = [
            ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
            ['blockquote', 'code-block'],

            [{ 'header': 1 }, { 'header': 2 }],               // custom button values
            [{ 'list': 'ordered'}, { 'list': 'bullet' }],
            [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
            [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
            [{ 'direction': 'rtl' }],                         // text direction

            [{ 'size': ['small', true, 'large', 'huge'] }],  // custom dropdown
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

            [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
            [{ 'font': [] }],
            [{ 'align': [] }],

            ['clean']                                         // remove formatting button
        ];

        quill = new Quill('#editor', {
            modules: {
                toolbar: toolbarOptions    // Snow includes toolbar by default
            },
            theme: 'snow'
        });

        var noteId = document.getElementById("noteId").value;
        var params = 'noteId=' + noteId;

        var http = new XMLHttpRequest();
        http.open("POST", "/note/getContent", false);
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        http.send(params);

        var content = http.responseText;
        quill.setContents(JSON.parse(content));
    }
}


function saveContent() {

    var json = JSON.stringify(quill.getContents())
    var notebookId = document.getElementById("notebookId").value;
    var noteId = document.getElementById("noteId").value;
    var params = 'notebookId=' + notebookId + '&noteId=' + noteId + '&content=' + json;

    let http = new XMLHttpRequest();
    http.open("POST", "/note/update", true);
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.send(params);

    location.reload();
}