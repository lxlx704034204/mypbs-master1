// 计算合计值
function sumFormatter(data) {
    var field = this.field;
    return data.reduce(function(sum, row) {
        return sum + (+row[field]);
    }, 0);
}
// 倒计时
var countdown = $(".countdown");
var countText = countdown.find("em");
var loading = countdown.find(".load").show();
var url = "/manage/product/index.jsp";
var timer = new Timer(url, 30);

loading.hide();
timer.start(function(count){
    if (count >= timer.updateInterval && !Loader.loading && !Loader.localUpdate){
        loading.show();
        $(".btnRefresh").click();
        timer.count = 0;
    }
    if (count >= 3600){
        count = (Math.floor(count / 3600)) + "&nbsp;时...";
    }else if(count >= 60){
        count = (Math.floor(count / 60)) + "&nbsp;分...";
    }else{
        count += "&nbsp;秒";
    }
    countText.html(count);
});

//立即更新
$(".btnRefresh").click(function(){
    var _this = this;
    if ($(_this).hasClass("disabled")) return false;
    $(_this).addClass("disabled");
    loading.show();
    timer.count = 0;
    $table.bootstrapTable('refresh',{url: url_json});
    $(_this).removeClass("disabled");
    loading.hide();
});
/**
 * Created by RX-005 on 2017/6/28.
 */
