/**
 * Created by Mefju on 2016-05-07.
 */
'use strict';
class AddBook {
    constructor(saveButton, elements) {
        this.saveButton = saveButton;
        this.elements = elements;
        alert( "Handler for .click() called." );
        this.renderEditor();

    }

    renderEditor() {
        $("#save").click(this.saveBook.bind(this))
    }

    saveBook() {
        alert( "Handler for .click() called." );
        $(this.saveButton).prop('disabled', true);
        $(this.saveButton).text("Saving...");

        $.ajax({
            url: '/abc',
            type: 'PUT',
            data: JSON.stringify(this.elements),
            contentType: 'application/json',
            success: function(result) {
                $(this.saveButton).text("Saved!");
                setTimeout(function() {
                    $(this.saveButton).prop('disabled', false);
                    $(this.saveButton).text("Save");
                }.bind(this), 1000);
            }.bind(this)
        });
    }
}