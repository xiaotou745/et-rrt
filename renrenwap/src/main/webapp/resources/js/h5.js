$(function() {
    var ratio = parseFloat(document.documentElement.clientWidth / 640);
    $('[data-pos]').each(function() {
        var ele = $(this);
        var pos = ele.attr('data-pos').split(',');
        ele.css('top', pos[1] * ratio).show();
    });
    $('[data-size]').each(function() {
        var ele = $(this);
        var pos = ele.attr('data-size').split(',');
        ele.css({
            'width': pos[0] * ratio,
            'height': pos[1] * ratio
        });
    });
})