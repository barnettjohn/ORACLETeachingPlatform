$(document).ready(function() {

    //将节点的类名命名成tipdelete tipedit 就可以实现整行的删除 和 编辑
    $(".tipdelete").click(function() {
        //点击删除
        this.closest('li').remove();
    });
    
    $(".tipedit").click(function() {
        var my_element = $(".form-actions");
        if (my_element.length > 0) {
            //alert("element is exist.");
        } else {
            //alert("element not be found");
            $a = "<div class='form-actions'> <button type='submit' class='btn btn-success'>上传此评分表</button> </div>";
            $(".form-horizontal").append($a);
        }

        //点击编辑

        $textlist = $(this).closest(".widget-box").find(".todotext");
        var q = 1;
        var p = 1;

        $textlist.each(function() {
            if ($(this).attr("name") == "question") {
                if (!$(this).is("input")) {
                    //alert($(this).text());
                    $input = $("<input >");

                    $input.attr("id", "question" + q);
                    $input.attr("name", "question" + q);
                    $input.attr("type", "text");
                    //$input.attr("class", "todotext");
                    $input.attr("value", $(this).text());
                    $(this).replaceWith($input);
                    q++;
                }
            } else if ($(this).attr("name") == "power") {
                if (!$(this).is("input")) {
                    //alert($(this).text());
                    $select = $("<select > <option >1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option> <select>");
                    $select.attr("id", "power" + p);
                    $select.attr("name", "power" + p);
                    $option = $select.val($(this).text());
                    $option.attr("selected",true);
                    //$select.s
                    $(this).replaceWith($select);
                    /*
                     $select.find("option").get($(this).text()).attr("selected","");

                     */
                    p++;
                }
            } else if ($(this).attr("name") == "fname") {
                if (!$(this).is("input")) {
                    //alert($(this).text());
                    $input = $("<input >");

                    $input.attr("id", "fname");
                    $input.attr("name", "fname");
                    $input.attr("type", "text");
                    $input.attr("value", $(this).text());
                    $(this).replaceWith($input);
                }
            } else if ($(this).attr("name") == "chaptid") {

                if (!$(this).is("input")) {
                    $select = $("<select > <select>");
                    $this = $(this);
                    $.post("GetChapterAjaxServlet", function(data, textStatus) {
                        //alert(data);
                        $select = $("<select > <select>");
                        $select.attr("id", "chapter");
                        $select.attr("name", "chapter");
                        $select.html(data);
                        //alert($select);

                        $this.replaceWith($select);
                    });

                }
                /*
                 if(!$(this).is("input")){
                 //alert($(this).text());

                 $select = $("<select > <select>");
                 $option = $(" <option></option>");
                 $option.attr("value", qid);
                 $option.text(qname);

                 $(this).replaceWith($select);
                 p++;
                 }*/

            }
        });

    });
});

