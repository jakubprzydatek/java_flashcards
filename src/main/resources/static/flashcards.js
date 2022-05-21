const $output = $("#flashcards");
const $categoryOutput = $("#categories");

$(document).ready(function() {
        $.get("http://localhost:8080/flashcard", function(data) {
            console.log(data)
            $output.html(
                data.map(item =>
                    `<div class="flip-card">
                        <div class="flip-card-inner">
                            <div class="flip-card-front">
                                ${item.name}
                            </div>
                            <div class="flip-card-back">
                                ${item.content}
                            </div>
                        </div>
                    </div>`)
            );
        });

        $.get("http://localhost:8080/category", function (data) {
            console.log(data)
            $categoryOutput.html(
                data.map(item =>
                `<option value="${item.id}">${item.name}</option>`)
            )
        })
});

$('#categories').on('change', function() {
    $output.html(``);
    let apiLink = "http://localhost:8080/category/" + this.value + "/flashcards"
    console.log(apiLink);
    $.get(apiLink, function(data) {
        console.log(data)
        $output.html(
            data.map(item =>
                `<div class="flip-card">
                        <div class="flip-card-inner">
                            <div class="flip-card-front">
                                ${item.name}
                            </div>
                            <div class="flip-card-back">
                                ${item.content}
                            </div>
                        </div>
                    </div>`)
        );
    });
});

