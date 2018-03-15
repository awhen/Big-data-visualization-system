// 数组去重
Array.prototype.unique = function(){
	this.sort();
	var res = [];
	var json = {};
	for (var i = 0; i < this.length; i++) {
		if(!json[this[i]]){
			res.push(this[i]);
			json[this[i]] = 1;
		}
	}
	return res;
}

// 对样式操作
var setClass = {
	hasClass: function(elements,cName){	// 判断是否含有某个class
		if(elements.className.match(new RegExp( "(\\s|^)" + cName + "(\\s|$)") ))
			return true;
		else
			return false;
	},
	addClass: function(elements,cName){	// 添加class
		if( !this.hasClass( elements,cName ) ){ 
			elements.className += " " + cName; 
		};
	},
	removeClass: function(elements,cName){	// 移除某个class
		if( this.hasClass( elements,cName ) ){ 
			elements.className = elements.className.replace( new RegExp( "(\\s|^)" + cName + "(\\s|$)" )," " ); 
		}
	}
}

var Bind = function(This){
	return function(){
		This.init();
	}
}

function AutoComplete(input,auto,arr) {
	this.obj = document.getElementById(input);
	this.autoObj = document.getElementById(auto);
	this.search_value = "";	//当前的搜索输入值
	this.index = -1;		//当前选中的DIV的索引
	this.value_arr = arr;	//数据库中供检索的值 不包含重复值
}
AutoComplete.prototype = {
	// 初始化
	init: function(){
		var This = this;
		setClass.removeClass(This.autoObj,"hidden");
		this.autoObj.style.left = this.obj.offsetLeft + "px";
		this.autoObj.style.top = this.obj.offsetTop + this.obj.offsetHeight + "px";
	},
	//删除自动完成需要的所有DIV
	deleteDIV: function(){
		while(this.autoObj.hasChildNodes()){
			this.autoObj.removeChild(this.autoObj.firstChild);
		}
		setClass.addClass(this.autoObj,"hidden");
	},
	autoOnmouseover: function(index){
		if(index != this.index){
			setClass.addClass(this.autoObj.children[index],"on");
			setClass.removeClass(this.autoObj.children[this.index],"on");
			this.index = index;
		}
	},
	setValue: function(This){
		return function(){
			This.obj.value = this.seq;
			setClass.addClass(This.autoObj,"hidden");
		}
	},
	// 响应键盘
	pressKey: function(event){
		var code = event.keyCode;
		var length = this.autoObj.children.length;
		if(code == 38){		//↑
			setClass.removeClass(this.autoObj.children[this.index],"on");
			this.index--;
			if(this.index < 0){
				this.index = length - 1;
			}
			setClass.addClass(this.autoObj.children[this.index],"on");
			this.obj.value = this.autoObj.children[this.index].seq;
		}else if(code == 40){	//↓
			setClass.removeClass(this.autoObj.children[this.index],"on");
			this.index++;
			if(this.index > length-1){
				this.index = 0;
			}
			setClass.addClass(this.autoObj.children[this.index],"on");
			this.obj.value = this.autoObj.children[this.index].seq;
		}else{			//回车
			this.obj.value = this.autoObj.children[this.index].seq;
			setClass.addClass(this.autoObj,"hidden");
			this.index = -1;
		}
	},
	// 程序入口
	start: function(event){
		event = event || window.event;
		var code = event.keyCode;
		var This = this;
		if(code != 13 && code != 38 && code != 40){
			this.init();
			this.deleteDIV();
			this.search_value = this.obj.value;
			var valueArr = this.value_arr.unique();
			//去掉前后空格不能为空
			if(this.obj.value.replace(/(^\s*)|(\s*$)/g,"") == ""){ return;}
			//判断数组中是否含有输入的关键字
			try{
				var reg = new RegExp("("+ this.obj.value +")","i");	//输入"aaa" 则 reg = /(aaa)/i
			}catch(e){
				alert(e.message); 
			}
			var div_index = 0;	//记录匹配索引个数
			for (var i = 0; i < valueArr.length; i++) {
				if(reg.test(valueArr[i])){
					var div = document.createElement("div");
					div.className = "auto_out";
					div.seq = valueArr[i];
					div.index = div_index;
					div.innerHTML = valueArr[i].replace(reg,"<strong>$1</strong>");
					this.autoObj.appendChild(div);
					setClass.removeClass(this.autoObj,"hidden");
					div_index++;
					if(div_index == 1) {
						setClass.addClass(this.autoObj.firstChild,"on");
						this.index = 0;
					}
					div.onmouseover = function(){
						This.autoOnmouseover(this.index);
					}
					div.onclick = this.setValue(This);
				}
			}
		}else{
			this.pressKey(event);
		}
		window.onresize = Bind(This);
	}
}