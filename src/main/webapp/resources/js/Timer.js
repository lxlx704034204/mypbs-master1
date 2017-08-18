/**
 * Created by liuyang on 2017/5/27.
 */

var Timer = function(key, updateInterval){
    this.updateInterval = updateInterval ? updateInterval : 20;
    this.count = 0;
    this.clearId = 0;
    this.key = key;
    this.storege = window.localStorage;
    this.firstTime = "";
    this.lastTime = "";
};

Timer.prototype.init = function(){
    var _this = this;
    this.stop();
    function difftime(){
        if (!_this.storege){
            return 0;
        }
        _this.firstTime = _this.storege.getItem(_this.key + "date");
        _this.lastTime = new Date(_this.getCurrentTime());
        if (_this.firstTime && _this.firstTime != "null"){
            _this.firstTime = new Date(_this.firstTime);
        }else{
            return 0;
        }
        var ms = _this.lastTime.getTime() - _this.firstTime.getTime();
        return Math.floor(ms / 1000);
    }
    this.count = difftime();
};

Timer.prototype.start = function(func){
    this.init();
    this.countDown(func);
};

Timer.prototype.countDown = function(func){
    var _this = this;
    func(_this.count++);
    this.clearId = setInterval(function(){
        func(_this.count++);
    }, 1000);
};

Timer.prototype.stop = function(func){
    clearInterval(this.clearId);
    if (typeof func == "function"){
        func(this);
    }
};

Timer.prototype.getCurrentTime = function(){
    var date = new Date();
    var strDate = "";
    var month = date.getMonth() + 1;
    strDate = date.getFullYear();
    strDate += "/";
    strDate += month < 10 ? "0" + month : month;
    strDate += "/";
    strDate += date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    strDate += " ";
    strDate += date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    strDate += ":";
    strDate += date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    strDate += ":";
    strDate += date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return strDate;
};