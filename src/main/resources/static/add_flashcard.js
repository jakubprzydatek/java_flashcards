const $categoryOutput = $("#checkboxes");

$(document).ready(function() {
    $.get("http://localhost:8080/category", function (data) {
        console.log(data)
        $categoryOutput.html(
            data.map(item =>
                `<input class="categoryInput" id="chkbx_${item.id}" category-id="${item.id}" type="checkbox" name="${item.shortName}" checked="checked" />${item.name}`)
        )
    })
});



$( "#addFlashcardBtn" ).click(function() {
    var selected = [];
    $('#checkboxes input:checked').each(function() {
        selected.push($(this).attr('category-id'));
    });
    console.log(selected);

    let idsJsonArray = '[ ' +  createCategoryIdsJsonArray(selected) + ' ]';

    const fname = $('textarea#fname').val();

    const fcontent = $('textarea#fcontent').val();

    let requestJson = '{ "categories": ' + idsJsonArray + ', "content": "' + fcontent + '", "id": 0, "name": "' + fname + '" }';

    console.log(requestJson);

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/flashcard',
        data: requestJson,
        success: function(data) { alert('data: ' + data); },
        contentType: "application/json",
        dataType: 'json'
    });
});

function createCategoryIdsJsonArray(categoriesIds)
{
    if(categoriesIds.length === 0)
    {
        return "null";
    }else if (categoriesIds.length === 0)
    {
        return '{"id":' + categoriesIds[0] + '}'
    }else
    {
        let idsArray = "";

        for (let i = 0; i < categoriesIds.length; i++) {
            if(i === categoriesIds.length - 1)
            {
                idsArray += '{"id":' + categoriesIds[i] + '}';
            }
            else{
                idsArray += '{"id":' + categoriesIds[i] + '},';
            }
        }

        return idsArray;
    }
}
