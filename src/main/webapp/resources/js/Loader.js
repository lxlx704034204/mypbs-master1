var Loader = {};
Loader.storege = window.localStorage;
Loader.localUpdate = false;
Loader.loading = false;
Loader.load = function(url, success){
    var _this = this;
    _this.loading = true;
    $.get(url, function(data){
        _this.loading = false;
        if (!_this.localUpdate){
            if (_this.storege){
                _this.storege.setItem(url, JSON.stringify(data));
                _this.storege.setItem(url + "isUpdate", 0);
                _this.storege.setItem(url + "date", _this.getCurrentTime());
            }
            success(data);
        }
    });
};
Loader.get = function(key){
    Loader.loading = false;
    if (arguments.length == 1){
        if (this.storege){
            return this.storege.getItem(key);
        }
    }
    if (arguments.length > 1){
        var url = key;
        var success = arguments[1];
        if (this.storege){
            var str = this.storege.getItem(url);
            var isUpdate = this.storege.getItem(url + "isUpdate");
            if ((str && str != "null")
                && (isUpdate && isUpdate != "null")
                && !parseInt(isUpdate)){
                success(JSON.parse(str));
            }else{
                this.load(url, success);
            }
        }else{
            this.load(url, success);
        }
    }
};

Loader.set = function(key, value){
    if (this.storege){
        this.storege.setItem(key, typeof value == "object" ? JSON.stringify(value) : value);
    }
};

Loader.remove = function(key){
    if (this.storege){
        this.removeItem(key);
    }
};

// 获取当前日期及时间
Loader.getCurrentTime = function(){
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