<template>
	<view style="height: 100%;background-color: #f1f1f1;">
		<view class="status_bar">
			<view class="top_view"></view>
		</view>
		<view class="cu-bar bg-orange-cu search ddd">
			<view class="wq-back" @click="back"><text class="cuIcon-back"></text></view>
			<view class="search-form search-input" style="border: 1px solid black;">
				<text class="icon iconfont cuIcon" style="color: gray;font-size: 18px;">&#xe783;</text>
				<input  @focus="InputFocus" :adjust-position="false" type="text" placeholder="输入关键字" confirm-type="search" @confirm="search" v-model="searchTerms.keyword"></input>
				<text class="text-gray cuIcon-voice" style="font-size: 18px;" @click="voiceClick()"></text>
			</view>
			<view class="margin-right-xs" @click="search">
				<text class="icon iconfont cuIcon" style="color: red;">&#xe783;</text>
			</view>
			<view class="action margin-right-xs" @tap="filter">
				<text class="icon iconfont cuIcon" style="color:#33A8C5">&#xe670;</text>
			</view>
			<view class="action" @tap="filterAgain">
				<text class="icon iconfont cuIcon" style="color:#1164AC">&#xe609;</text>
			</view>
			<view v-if="showThirdFilter" class="action" @tap="filterThird">
				<text class="icon iconfont cuIcon" style="color:#022875">&#xe75e;</text>
			</view>
		</view>
		<view class="uni-tab-bar">
			<scroll-view id="tab-bar" class="uni-swiper-tab" scroll-x :scroll-left="scrollLeft">
				<view v-for="(tab,index) in tabBars" :key="tab.id" class="swiper-tab-list" :class="tabIndex==index ? 'active' : ''" :id="tab.id"
				 :data-current="index" @click="tapTab">{{tab.name}}</view>
			</scroll-view>
			<view class="content-box">
				<swiper :current="tabIndex" class="swiper-box" :duration="300" @change="changeTab">
					<swiper-item v-for="(tab,index1) in allColumnDatas" :key="index1">
						<view class="search">共{{tab.totalRecord}}条结果 <text class="mark" @click="markUnsolved()">没有满意答案</text></view> 
						<scroll-view class="list" scroll-y @scrolltolower="loadMore()"  :scroll-top="scrollTop" @scroll="scroll">
							<block v-for="(singleColumnData,index2) in tab.data" :key="index2">
								<media-list :options="singleColumnData" :tabIndex="tabIndex"  @click="goDetail(singleColumnData)"></media-list>
							</block>
							<view class="uni-tab-bar-loading">
								{{tab.loadingText}}
							</view>
						</scroll-view>
					</swiper-item>
				</swiper>
			</view>
		</view>
		<!--滚动到最上面的按钮-->
		<view v-if="showGoTopBtn" class="top-box cu-avatar round" @click="goTop"><text class="lg cuIcon-top" style="color: #81D3E0;"></text></view>
		<!--高级查询框-->
		<uni-drawer :visible="showRight" :mask="true" class="wq-dialog" mode="right" @close="closeDrawer()">
			<form>
				<scroll-view scroll-y>
					<scroll-view v-if="isHighSearch" id="task-drawer-scroll">
						<view class="cu-form-group" style="margin-top: 60upx;">
							<view class="title">标题</view>
							<input placeholder="请输入" name="title" v-model="searchTerms.title"></input>
						</view>
						<view class="cu-form-group">
							<view class="title">标签</view>
							<picker @change="labelChange" :range-key="'name'" :range="labelsArr" mode="selector" v-model="labelsArr">
								<view class="picker">
									{{labelStatus>-1?labelsArr[labelStatus].name:'请选择'}}
								</view>
							</picker>
						</view>
						<view class="cu-form-group">
							<view class="title">备注</view>
							<input placeholder="请输入" name="remark" v-model="searchTerms.remark">
						</view>
						<view class="cu-form-group">
							<view class="title">类别</view>
							<picker @change="typeChange" :range-key="'value'" :range="fileTypeArr" mode="selector" v-model="fileTypeArr">
								<view class="picker">
									{{typeStatus>-1?fileTypeArr[typeStatus].value:'请选择'}}
								</view>
							</picker>
						</view>
						<view  class="cu-form-group">
							<view class="title">创建人</view>
							<!-- <input placeholder="请输入" name="createBy" v-model="searchTerms.createBy"></input> -->
							<view class="text-right" @tap="chooseUser" style="color:#333333">
								{{selectedUser.length>0?selectedUser[0].name:'请选择'}}<text class="cuIcon-right"></text>
							</view>
						</view>
						<view class="cu-form-group time-bg">
							<view class="title">开始时间</view>
							<picker mode="date" :value="startTime" @change="startDateChange">
								<view class="picker">
									{{parseDate(startTime)}}
								</view>
							</picker>
						</view>
						<view class="cu-form-group time-bg">
							<view class="title">结束时间</view>
							<picker mode="date" :value="endTime" @change="endDateChange">
								<view class="picker">
									{{parseDate(endTime)}}
								</view>
							</picker>
						</view>
						<view class="cu-form-group">
							<view class="title">文件名称</view>
							<input placeholder="请输入" name="name" v-model="searchTerms.name"></input>
						</view>
						<view class="cu-form-group">
							<view class="title">文件内容</view>
							<input placeholder="请输入" name="content" v-model="searchTerms.content"></input>
						</view>
						<view class="cu-form-group">
							<view class="title">文件格式</view>
							<input placeholder="请输入" name="suffix" v-model="searchTerms.suffix"></input>
						</view>
					</scroll-view>
					<scroll-view v-if="isSecondSearch" id="filter-scroll-view">
						<view class="cu-form-group" style="margin-top: 60upx;">
							<view class="title">二次筛选</view>
							<input placeholder="请输入" name="secondKeyWord" v-model="searchTerms.secondKeyWord">
						</view>
					</scroll-view>
					<scroll-view v-if="isThirdSeach" id="third-filter-scroll-view">
						<view class="cu-form-group" style="margin-top: 60upx;">
							<view class="title">三次筛选</view>
							<input placeholder="请输入" name="thirdKeyWord" v-model="searchTerms.thirdKeyWord">
						</view>
					</scroll-view>
					<view v-if="isHighSearch" class="cu-bar-cu" id="highSearch">
						<button class="uni-button-cu reset-cu bg-gray" @click="highResetBtn()">关闭</button>
						<button class="uni-button-cu confirm-cu bg-btn-color" form-type="submit" @click="highSearch()">确定</button>
					</view>
					<view v-if="isSecondSearch" class="cu-bar-cu" id="filterSearch">
						<button class="uni-button-cu reset-cu bg-gray" @click="filterResetBtn()">关闭</button>
						<button class="uni-button-cu confirm-cu bg-btn-color" @click="filterSearch()">确定</button>
					</view>
					<view v-if="isThirdSeach" class="cu-bar-cu" id="filterSearch">
						<button class="uni-button-cu reset-cu bg-gray" @click="thirdFilterResetBtn()">关闭</button>
						<button class="uni-button-cu confirm-cu bg-btn-color" @click="thirdFilterSearch()">确定</button>
					</view>
				</scroll-view>
			</form>
		</uni-drawer>
		<view class="cu-load load-modal" v-if="loadModal">
			<image src="/static/logo.png" mode="aspectFit"></image>
			<view class="gray-text">加载中...</view>
		</view>
		<view class="cu-modal bottom-modal" :class="showModal?'show':''" @click="hideModal()">
			<view class="cu-dialog" @tap.stop="editText">
				<view class="padding-xl voice-box">
					{{voiceText}}
				</view>
				<view>
					<view class="flex flex-wrap justify-center align-center">
						<view class="text-sm text-gray">按住说话</view>
					</view>
				</view>
				<view class="padding-xl">
					<view class="padding flex flex-wrap justify-between align-center">
						<view class="modal-font" @tap.stop="clearData">清空</view>
						<view class="round">
							<text class="lg text-gray cuIcon-voice voice-icon" :class="voiceColor?'voice-color':''" @tap.stop=""  @touchstart.stop="voiceTouchStart" @touchend.stop="voiceTouchEnd"></text>
							</view>
							<view v-if="isShowLoading"  class="loading"></view>
						<view class="modal-font" @tap.stop="sendData">发送</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	const recorderManager = uni.getRecorderManager();
	const innerAudioContext = uni.createInnerAudioContext();
	import uniDrawer from '@/components/uni-drawer/uni-drawer.vue';
	import $DataUtils from '@/common/utils/dataUtils.js';
	import mediaList from '@/components/tab-nvue/mediaList.vue';

	export default {
		components: {
			uniDrawer,
			mediaList
		},
		data() {
			return {
				//语音转文字参数
				showModal:false,
				voiceText:"",
				voiceColor:false,
				voicePath: '',
				sendVoiceText:"",
				isShowLoading:false,
				isHighSearch:true,
				isSecondSearch:false,
				isThirdSeach:false,
				showRight:false,
				labelStatus: -1,
				typeStatus:-1,
				fileTypeArr:[],
				labelsArr:[],
				startTime: '',
				endTime: '',
				focus:false,
				showThirdFilter:false,
				totalRecord:0,
				selectedUser:[],
				loadModal: true,
				searchTerms: {
					keyword:'',
					title: '',
					remark: '',
					createBy: '',
					name: '',
					content: '',
					suffix: '',
					label: '',
					type: '',
					createTimeStr:'',
					fileType:'',
					searchType:'',
					pageNo:1,
					pageSize:10,
					app:true,
					secondKeyWord:""
				},
				scrollLeft: 0,
				basicFileTypeArr:["",-1,0,1,2,4],
				
				lastTimeTabIndex:0,
				isClickChange: false,
				tabIndex: 0,
				allColumnDatas: [],
				tabBars: [
					{name:"全部",id:"all",value:-1},
					{name:"WORD",id:"word",value:2},
					{name:"TXT",id:"txt",value:1},
					{name:"PDF",id:"pdf",value:4},
					{name:"PPT",id:"ppt",value:6},
					{name:"图片",id:"img",value:3},
					{name:"视频",id:"video",value:5},
					{name:"其他",id:"other",value:0}
					],
					showGoTopBtn:false,
					scrollTop: 0,
					old: {
						scrollTop: 0
					}
			}
		},
		onLoad:function(option){
			//初始化语音模块
			let self = this;
			recorderManager.onStop(function (res) {
			    self.voicePath = res.tempFilePath;
				self.uploadAudio();
				self.isShowLoading = true;
				console.log("self.voicePath",self.voicePath);
			});
			
			this.searchTerms = uni.getStorageSync("searchTerms");
			uni.setStorageSync("searchTerms",{});
			uni.$on('getSelectedUser2',this.getSelectedUser);
			//获取搜索的类型
			var arr = Object.keys(option);
			if(arr.length != 0){
				this.searchTerms.searchType = option.searchType;
			}
			this.initDict("FileType",this.fileTypeArr);
			this.initLables();
		},
		destroyed:function(){
			uni.$off('getSelectedUser2',this.getSelectedUser);
		},
		onShow() {
			this.initTime();
			//根据查询条件搜索后台数据
			this.initData();
			//显示隐藏三次筛选
			if(this.searchTerms.secondKeyWord){
				this.showThirdFilter = true;
			}else{
				this.showThirdFilter = false;
			}
		},
		methods: {
			getSelectedUser(){
				this.selectedUser = uni.getStorageSync("selectedUser");
				if(this.selectedUser&&this.selectedUser.length>0){
					this.searchTerms.createBy = this.selectedUser[0].account;
				}
			},
			chooseUser(){
				uni.navigateTo({
					url: '../common/user_list?lastPage=HOME_DETAIL'
				});
			},
			goDetail(data) {
				let fileType = data.fileType;
				let filePath = data.filePath;
				let fileId = data.id;
				let name = data.name;
				let content = data.content;
				let keyContentArr = content.match(/<span style="color:red">([\s\S]{1,10})<\/span>/g);
				let keyWordArr = [];
				if(keyContentArr&&keyContentArr.length>0){
					for(let i=0;i<keyContentArr.length;i++){
						let keyWord = keyContentArr[i].replace('<span style="color:red">','').replace("</span>",'');
						keyWordArr.push(keyWord);
					}
				}
				let keyWord="";
				if(keyWordArr&&keyWordArr.length>0){
					for(let i=0;i<keyWordArr.length;i++){
						if(i==0){
							keyWord = keyWordArr[i];
							continue;
						}
						if(keyWordArr[i].length>2){
							keyWord = keyWordArr[i];
						}
					}
				}
				//如果内容中没有关键字段，在标题中查找
				if(keyWord==""){
					keyContentArr = name.match(/<span style="color:red">([\s\S]{1,10})<\/span>/g);
					if(keyContentArr&&keyContentArr.length>0){
						for(let i=0;i<keyContentArr.length;i++){
							let keyWord = keyContentArr[i].replace('<span style="color:red">','').replace("</span>",'');
							keyWordArr.push(keyWord);
						}
					}
					if(keyWordArr&&keyWordArr.length>0){
						for(let i=0;i<keyWordArr.length;i++){
							if(i==0){
								keyWord = keyWordArr[i];
								continue;
							}
							if(keyWordArr[i].length>2){
								keyWord = keyWordArr[i];
							}
						}
					}
				}
				
				//其他文件，下载
				if(fileType==0){
					return;
				}
				//图片文件
				if(fileType==3){
					//页面层-图片
					var imgUrl = this.$request.config.baseUrl+"no/download?filePath="+filePath+"&name="+name;
					uni.navigateTo({
						url: '/pages/search/img_detail?imgUrl='+imgUrl
					}) 
					return;
				}
				//视频
				if(fileType==5){
					/* var videoUrl = this.$request.config.baseUrl+"no/download?filePath="+filePath+"&name="+name; */
					uni.navigateTo({
						url: '/pages/search/video_detail?filePath='+filePath+"&name="+name
					}) 
					return;
				}
				uni.navigateTo({
					url: '/pages/search/pdf_detail?filePath=' + filePath+"&keyword="+keyWord+"&content="+this.searchTerms.content+"&searchType="+this.searchTerms.searchType+"&fileId="+fileId
				})
			},
			close(index1, index2) {
				uni.showModal({
					content: '是否删除本条信息？',
					success: (res) => {
						if (res.confirm) {
							this.allColumnDatas[index1].data.splice(index2, 1);
						}
					}
				})
			},
			loadMore(e) {
				//加载更多，需要获取上次的文件类型和这次的文件类型。用来看pageNo是第几页
				var pageNo = this.allColumnDatas[this.tabIndex].pageNo;
				let loadingTextTemp = this.allColumnDatas[this.tabIndex].loadingText;
				if(this.loadingTextTemp != "没有更多"){
					this.allColumnDatas[this.tabIndex].loadingText = "加载中";
					pageNo = pageNo + 1;
					this.searchTerms.pageNo = pageNo;
					if(pageNo>1){
						this.showGoTopBtn = true;
					}
					this.$request.get("attachment/pagedlist", this.searchTerms)
					.then(res => {
						this.loadModal = false;
						if(res.data.code!="Success"){
							return;
						}
						let loadingTextTemp = this.changeLoadingText(res.data);
						let totalRecordTemp = res.data.totalRecord;
						let dataTemp = res.data.data;
						dataTemp = this.getExtendDataTemp(dataTemp);
						var oldData = this.allColumnDatas[this.tabIndex].data;
						for(var i=0;i<dataTemp.length;i++){
							oldData.push(dataTemp[i]);
						}
						let singleColumnData = {
								loadingText: loadingTextTemp,
								totalRecord:totalRecordTemp,
								pageNo:pageNo,
								data: oldData
							};
						this.allColumnDatas.splice(this.tabIndex, 1, singleColumnData);
					});
				}
				
			},
			async changeTab(e) {
				this.lastTimeTabIndex = this.tabIndex;
				let index = e.target.current;
				if (this.isClickChange) {
					this.tabIndex = index;
					this.isClickChange = false;
					return;
				}
				let tabBar = await this.getElSize("tab-bar"),
					tabBarScrollLeft = tabBar.scrollLeft;
				let width = 0;

				for (let i = 0; i < index; i++) {
					let result = await this.getElSize(this.tabBars[i].id);
					width += result.width;
				}
				let winWidth = uni.getSystemInfoSync().windowWidth,
					nowElement = await this.getElSize(this.tabBars[index].id),
					nowWidth = nowElement.width;
				if (width + nowWidth - tabBarScrollLeft > winWidth) {
					this.scrollLeft = width + nowWidth - winWidth;
				}
				if (width < tabBarScrollLeft) {
					this.scrollLeft = width;
				}
				this.isClickChange = false;
				this.tabIndex = index; //一旦访问data就会出问题
				if(this.lastTimeTabIndex != this.tabIndex){
					this.searchTerms.pageNo=1;
					this.queryData();
				}
				this.searchTerms.fileType = this.tabBars[this.tabIndex].value;
				uni.setStorageSync("searchTerms",this.searchTerms);
			},
			getElSize(id) { //得到元素的size
				return new Promise((res, rej) => {
					uni.createSelectorQuery().select("#" + id).fields({
						size: true,
						scrollOffset: true
					}, (data) => {
						res(data);
					}).exec();
				})
			},
			async tapTab(e) { //点击tab-bar
				this.lastTimeTabIndex = this.tabIndex;
				let tabIndex = e.target.dataset.current;
				if (this.tabIndex === tabIndex) {
					return false;
				} else {
					let tabBar = await this.getElSize("tab-bar"),
						tabBarScrollLeft = tabBar.scrollLeft; //点击的时候记录并设置scrollLeft
					this.scrollLeft = tabBarScrollLeft;
					this.isClickChange = true;
					this.tabIndex = tabIndex;
				}
				if(this.lastTimeTabIndex != this.tabIndex){
					this.searchTerms.pageNo=1;
					this.queryData();
				}
				this.searchTerms.fileType = this.tabBars[this.tabIndex].value;
				uni.setStorageSync("searchTerms",this.searchTerms);
			},
			
			InputFocus() {
				this.focus = true;
			},
			back(){
				uni.reLaunch({
					url: "/pages/index/index"
				})
			},
			search(){
				this.searchTerms.searchType = "basicSearch";
				this.initData();
			},
			filter(e){
				this.isHighSearch = true;
				this.isSecondSearch = false;
				this.isThirdSeach = false;
				this.showRight = true;
			},
			filterAgain(e){
				this.isHighSearch = false;
				this.isSecondSearch = true;
				this.isThirdSeach = false;
				this.showRight = true;
			},
			filterThird(e){
				this.isHighSearch = false;
				this.isSecondSearch = false;
				this.isThirdSeach = true;
				this.showRight = true;
			},
			hideModal(e) {
				this.showModal = false;
			},
			highResetBtn(){
				this.showRight = false;
			},
			filterResetBtn(){
				this.showRight = false;
			},
			thirdFilterResetBtn(){
				this.showRight = false;
			},
			highSearch(searchTypeTemp){
				this.showRight = false;
				this.searchTerms.createTimeStr = this.startTime +"~"+this.endTime;
				this.searchTerms.createTimeStr=this.searchTerms.createTimeStr=="~"?"":this.searchTerms.createTimeStr;
				if(this.typeStatus!=-1){
					this.searchTerms.type = this.fileTypeArr[this.typeStatus].key;
				}
				if(this.labelStatus!=-1){
					this.searchTerms.label = this.labelsArr[this.labelStatus].id;
				}
				if(searchTypeTemp&&searchTypeTemp!=""){
					this.searchTerms.searchType = searchTypeTemp;
				}else{
					this.searchTerms.searchType = "highSearch";
				}
				console.log("this.searchTerms.searchType",this.searchTerms.searchType);
				this.initData();
			},
			filterSearch(){
				this.loadModal = true;
				if(this.searchTerms.secondKeyWord){
					this.showThirdFilter = true;
				}else{
					this.showThirdFilter = false;
				}
				this.highSearch("filterSearch");
			},
			thirdFilterSearch(){
				this.loadModal = true;
				if(this.searchTerms.secondKeyWord){
					this.showThirdFilter = true;
				}else{
					this.showThirdFilter = false;
				}
				this.highSearch("thirdFilterSearch");
			},
			markUnsolved(){
				var search = this.searchTerms.keyword;
				if(search==""){
					uni.showToast({
						icon: 'none',
						position: 'center',
						title: "无搜索条件无需标记"
					});
				    return;
				}
				this.$request.post("attachment/markUnsolved", {search:search})
				.then(res => {
					let msg = res.data.data;
					uni.showToast({
						icon: 'none',
						position: 'center',
						title: msg
					});
				});
			},
			//改变加载数据
			changeLoadingText(responsedata){
				let loadingTextTemp="";
				if(responsedata.data.length != 10){
					loadingTextTemp = "没有更多";
				}else{
					loadingTextTemp = "加载更多";
				}
				return loadingTextTemp;
			},
			closeDrawer() {
				this.showRight = false;
			},
			labelChange(e) {
				this.labelStatus = e.detail.value;
			},
			typeChange(e) {
				this.typeStatus = e.detail.value;
			},
			startDateChange(e) {
				this.startTime = e.detail.value;
			},
			endDateChange(e){
				this.endTime = e.detail.value;
			},
			parseDate(data){
				if(data){
					return $DataUtils.formatDate(new Date(data),"yyyy-MM-dd");
				}
				return "请选择";
			},
			initLables(){
				this.$request.get("labels", {
						pageSize: -1,
						account:uni.getStorageSync('user').account
					}).then(res => {
						let respData = res.data;
						let resultData = res.data.data;
						if (respData.msg == "Success") {
							for (let data of resultData) {
								let item = {};
								item.id = data.id;
								item.name = data.name;
								item.checked = false;
								this.labelsArr.push(item);
							}
							//初始化标签
							if(this.searchTerms.label==-1){
								return;
							}
							for(let i=0;i<this.labelsArr.length;i++){
								if(this.searchTerms.label==this.labelsArr[i].id){
									this.labelStatus = i;
								}
							}
						}
					});
			},
			initDict(code,arr){
				this.$request.get("dict/item/code", {
						code: code
					}).then(res => {
						let respData = res.data;
						let resultData = res.data.data;
						if (respData.msg == "Success") {
							for (let data of resultData) {
								let item = {};
								item.key = data.key;
								item.value = data.value;
								item.checked = false;
								arr.push(item);
							}
							let item = {};
							item.key = "-1";
							item.value = "全部";
							item.checked = false;
							arr.splice(0,0,item);
							//初始化类别
							 for(let i=0;i<this.fileTypeArr.length;i++){
								 if(this.fileTypeArr[i].key==this.searchTerms.type){
									this.typeStatus = i;
								 }
							 }
						}
					});
			},
			//初始化搜索的时间
			initTime(){
				 if(this.searchTerms.createTimeStr&&this.searchTerms.createTimeStr!=""){
					let timeArr = this.searchTerms.createTimeStr.split("~");
					this.startTime = timeArr[0];
					this.endTime = timeArr[1];
				 }
			},
			initData(){
				this.allColumnDatas = [];
				for (let i = 0, length = this.tabBars.length; i < length; i++) {
					let aryItem = {
						loadingText: '没有更多...',
						totalRecord:0,
						pageNo:1,
						data: [],
						index:i
					};
					this.allColumnDatas.push(aryItem);
				}
				this.$request.get("attachment/pagedlist", this.searchTerms)
				.then(res => {
					this.loadModal = false;
					if(res.data.code!="Success"){
						return;
					}
					let loadingTextTemp = this.changeLoadingText(res.data);
					let totalRecordTemp = res.data.totalRecord;
					let dataTemp = res.data.data;
					//这里需要增强dataTemp中标红的数据
					dataTemp = this.getExtendDataTemp(dataTemp);
					let singleColumnData = {
							loadingText: loadingTextTemp,
							totalRecord:totalRecordTemp,
							pageNo:1,
							data: dataTemp,
							index:this.tabIndex
						};
					this.allColumnDatas.splice(this.tabIndex, 1, singleColumnData);
				});
			},
			//扩展 需要填充的数据
			getExtendDataTemp(dataTemp){
				if(!(dataTemp&&dataTemp.length>0)){
					return dataTemp;
				}
				var keyword = this.searchTerms.keyword;
				var secondKeyWord = this.searchTerms.secondKeyWord;
				var thirdKeyWord = this.searchTerms.thirdKeyWord;
				//data-tag表示前端页面创建的
				var prefix = '<span style="color:red">';
				var prefixTag = '<span style="color:red" data-tag="js">'; 
				var suffix = '</span>';
				for (var i = 0; i < dataTemp.length; i++) {
					var content = dataTemp[i].content;
					if(keyword&&keyword!=""){
						var redKeyWord = prefix + keyword + suffix;
						if(content&&content.indexOf(redKeyWord)==-1&&content.indexOf(keyword)!=-1){
							var redTagKeyWord = prefixTag + keyword + suffix;
							content = this.replaceAll(content,keyword,redTagKeyWord);
						}
					}
					if(secondKeyWord&&secondKeyWord!=""){
						var redSecondKeyWord = prefix+secondKeyWord + suffix;
						if(content&&content.indexOf(redSecondKeyWord)==-1&&content.indexOf(secondKeyWord)!=-1){
							var redTagSecondKeyWord = prefixTag+secondKeyWord + suffix;
							content = this.replaceAll(content,secondKeyWord,redTagSecondKeyWord);
						}
					}
					if(thirdKeyWord&&thirdKeyWord!=""){
						var redThirdKeyWord = prefix+thirdKeyWord +suffix;
						if(content&&content.indexOf(redThirdKeyWord)==-1&&content.indexOf(thirdKeyWord)!=-1){
							var redTagThirdKeyWord = prefixTag+thirdKeyWord + suffix;
							content = this.replaceAll(content,thirdKeyWord,redThirdKeyWord);
						}
					}
					dataTemp[i].content = content;
				}
				return dataTemp;
			},
			 replaceAll(content,s1, s2) {
			    return content.replace(new RegExp(s1, "gm"), s2);
			 },
			//搜索数据
			queryData(pageNo){
				this.searchTerms.fileType =this.tabBars[this.tabIndex].value;
				this.$request.get("attachment/pagedlist", this.searchTerms)
				.then(res => {
					this.loadModal = false;
					if(res.data.code!="Success"){
						return;
					}
					let loadingTextTemp = this.changeLoadingText(res.data);
					let totalRecordTemp = res.data.totalRecord;
					let dataTemp = res.data.data;
					dataTemp = this.getExtendDataTemp(dataTemp);
					let singleColumnData = {
							loadingText: loadingTextTemp,
							totalRecord:totalRecordTemp,
							pageNo:1,
							data: dataTemp,
							index:this.tabIndex
						};
					this.allColumnDatas.splice(this.tabIndex, 1, singleColumnData);
				});
			},
			formateBasicTitle(item){
				if(item.fileType==1||item.fileType==2||item.fileType==4){
					return item.name;
				}else{
					return item.title!=null&&item.title!=""?item.title:item.name;
				}
			},
			//格式化类别
			formateType(sysDictItem){
				if(sysDictItem!=null&&sysDictItem!=undefined){
					return sysDictItem.value;
				}
				return "";
			},
			formateDate(time){
				return $DataUtils.formatDate(new Date(time),"yyyy年MM月dd日");
			},
			goTop(){
				this.scrollTop = this.old.scrollTop
				this.$nextTick(function() {
					this.scrollTop = 0
					uni.pageScrollTo({
					    scrollTop: 0,
					    duration: 100
					});
				});
				
				this.showGoTopBtn = false;
			},
			scroll: function(e) {
				this.old.scrollTop = e.detail.scrollTop
				if(this.old.scrollTop>=600){
					this.showGoTopBtn = true;
				}else{
					this.showGoTopBtn = false;
				}
			},
			voiceClick(){
				this.showModal = !this.showModal;
				this.voiceText = '';
				this.sendVoiceText = '';
			},
			clearData(){
				this.voiceText="";
			},
			sendData(){
				this.showModal = false;
				this.sendVoiceText = this.voiceText;
				this.searchTerms.keyword = this.sendVoiceText;
				this.search();
			},
			editText(){
				if(this.voiceText==""){
					return;
				}
				uni.setStorageSync('searchText', this.voiceText);
				uni.navigateTo({
					url: "/pages/search/voice_edit"
				})
			},
			voiceTouchStart(){
				this.voiceColor = true;
				recorderManager.start({"sampleRate":16000,"numberOfChannels":1,"format":"mp3"});
			},
			voiceTouchEnd(){
				this.voiceColor = false;
				recorderManager.stop();
				
			},
			uploadAudio(){
				let url = this.$request.config.baseUrl + 'attachment/uploadFile';
				uni.uploadFile({
					url: url, //仅为示例，非真实的接口地址
					filePath: this.voicePath,
					name: 'file',
					header: {
						'Content-Type': 'multipart/form-data'
					},
					success: (res) => {
						this.isShowLoading = false;
						let data = JSON.parse(res.data);
						if(data.code=="Success"){
							this.voiceText += data.data;
						}
					},
					fail:(res)=>{
						this.isShowLoading = false;
					}
				});
			}
		}
	}
</script>

<style>
	.uni-tab-bar {
		display: flex;
		flex: 1;
		flex-direction: column;
		overflow: hidden;
		height: calc(100% - 200upx);
	}
	.uni-tab-bar-loading{
		text-align: center;
		font-size: 28upx;
		color: #999;
		padding-bottom: 60upx;
	}
	.uni-swiper-tab {
		width: 100%;
		white-space: nowrap;
		line-height: 100upx;
		height: 100upx;
		border-bottom: 1px solid #c8c7cc;
	}
	.swiper-tab-list {
		font-size: 30upx;
		width: 150upx;
		display: inline-block;
		text-align: center;
		color: #555;
	}
	.uni-tab-bar .active {
	    color: #007AFF;
	}
	.uni-tab-bar .swiper-box {
		flex: 1;
		width: 100%;
		height: calc(100vh - 246upx);
	}
	.uni-tab-bar .list {
		width: 750upx;
		height: 100%;
	}
	uni-page-body {
	    background-color: #F4F5F6;
	    height: 100%;
	    font-size: 14px;
	    line-height: 1.8;
	}
	.ddd{
		padding: 0;
		margin: 0;
		width: 100%;
		z-index: 301;
		min-width: 100px;
	}
	.content-box{
		height: 100%;
		width: 100%;
	}
	
	.status_bar {
	    height: var(--status-bar-height);  
	    width: 100%;  
	}  
	.top_view {
	    height: var(--status-bar-height);  
	    width: 100%;  
	    position: fixed;  
	    top: 0;  
	    z-index: 999;  
	} 
	.cu-bar-cu {
		display: -webkit-box;
		display: -webkit-flex;
		display: flex;
		position: relative;
		-webkit-box-align: center;
		-webkit-align-items: center;
		align-items: center;
		min-height: 50px;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		justify-content: space-between;
		width: 100%;
		height: 100rpx;
		position: fixed;
		bottom: 0;
	}
	.reset-cu {
		width: 50%;
		height: 100rpx;
		color: #8a8a8a;
		line-height: 100rpx;
		border-radius: 0;
	}
	
	.confirm-cu {
		width: 50%;
		height: 100rpx;
		color: #ffffff;
		line-height: 100rpx;
		border-radius: 0;
	}
	.top-box{
		position: absolute;
		bottom: 70upx;
		right: 80upx;
		font-size: 70upx;
	}
	.cu-avatar{
		width: 40px;
		height: 40px;
		background-color: #22222270;
	}
	.bg-btn-color{
		    background-color: #81D3E0;
	}
	.uni-input-input {
	    font-size: 15px;
	}
	.uni-input-placeholder {
	    font-size: 12px;
	}
	.cu-bar{
		min-height: 100upx;
	}
	.cu-bar .search-form {
	    line-height: 34px;
	    height: 34px;
		margin: 0 10px;
	}
	.cu-bar .action>uni-text[class*="cuIcon-"], .cu-bar .action>uni-view[class*="cuIcon-"] {
	    font-size: 18px;
	}
	.wq-back{
		padding-left: 20upx;
	}
	.search-input{
		border-radius: 10px;
		border: 1px solid gray;
		background-color: white;
	}
	.wq-dialog{
		top: var(--status-bar-height);
	}
	.mark{
		padding-left: 20upx;
	}
	.mark:hover{
		color:blue;
	}
	.voice-box{
		height: 420upx;
	}
	.voice-icon{
		font-size: 56upx;
	}
	.modal-font{
		font-size: 32upx;
	}
	.voice-color{
		color: green;
	}
	.cu-modal{
		text-align: left;
	}
	.loading {
		position: absolute;
		left: calc(50% - 40upx);
		width: 80upx;
		height: 80upx;
		border: 6upx solid green;
		-webkit-border-radius: 50%;
		border-radius: 50%;
		display: inline-block;
		vertical-align: middle;
		-webkit-clip-path: polygon(0% 0%, 100% 0%, 100% 40%, 0% 40%);
		clip-path: polygon(0% 0%, 100% 0%, 100% 40%, 0% 40%);
		-webkit-animation: rotate-data-v-350f4502 1s linear infinite;
		animation: rotate-data-v-350f4502 1s linear infinite;
		-webkit-animation: cuIcon-spin 2s infinite linear;
		animation: cuIcon-spin 2s infinite linear;
		}
</style>