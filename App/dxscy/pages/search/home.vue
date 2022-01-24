<template name="search">
	<view style="height: 100%;background-color: #f1f1f1;">
		<view class="status_bar">
			<view class="top_view"></view>
		</view>
		<view class="body-bg" style="background-image: url(../../static/img/bg_app.jpg);background-size: 100%;">
			<!-- <view><image class="img" src="../../static/img/logo.png" alt=""></image></view> -->
			<view class="img-layout">医疗智能检索系统</view>
			<view class="cu-bar bg-orange-cu search">
				<view class="search-form search-input">
					<text class="icon iconfont cuIcon" style="color: gray;font-size: 18px;">&#xe783;</text>
					<input @focus="InputFocus" :adjust-position="false" type="text" placeholder="输入关键字" confirm-type="search" @confirm="search" v-model="searchTerms.keyword"></input>
				</view>
				<view v-if="focus" class="action" @click="search">
					<text class="icon iconfont cuIcon" style="color: red;">&#xe783;</text>
				</view>
				<view v-if="focus" class="action" @tap="filter" data-target="DrawerModalR">
					<text class="icon iconfont cuIcon" style="color:rgb(2, 215, 255)">&#xe670;</text>
				</view>
			</view>
			<uni-drawer :visible="showRight" :mask="true" class="wq-dialog" mode="right" @close="closeDrawer()">
				<form>
					<scroll-view scroll-y>
						<scroll-view id="task-drawer-scroll" scroll-y>
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
							<view class="cu-form-group">
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
						<view class="cu-bar-cu">
							<button class="uni-button-cu reset-cu bg-gray" @click="reset">关闭</button>
							<button class="uni-button-cu confirm-cu bg-btn-color" form-type="submit" @click="highSearch">确定</button>
						</view>
					</scroll-view>
				</form>
			</uni-drawer>
		</view>
	</view>
</template>

<script>
	import uniDrawer from '@/components/uni-drawer/uni-drawer.vue'
	import $DataUtils from '@/common/utils/dataUtils.js';

	export default {
		components: {
			uniDrawer
		},
		props:{
			searchText:String
		},
		watch:{
			searchText(){
				this.searchData();
			}
		},
		data() {
			return {
				focus: true,
				TabCur: 0,
				labelStatus: -1,
				typeStatus: -1,
				showRight: false,
				fileTypeArr: [],
				labelsArr: [],
				startTime: '',
				endTime: '',
				modelBottom: false,
				selectedUser:[],
				searchTerms: {
					keyword:'',
					title: '',
					remark: '',
					createBy: '',
					name: '',
					content: '',
					suffix: '',
					createTimeStr: '',
					label: '',
					type: '',
					createTimeStr:'',
					fileType:'',//-1:全部
					searchType:'',
					pageSize:10,
					app:true
				}
			};
		},
		created: function() {
			this.checkIp();
			this.startTime = "";
			this.endTime = "";
			this.initDict("FileType", this.fileTypeArr);
			this.initLables();
			uni.$on('getSelectedUser1',this.getSelectedUser);
		},
		methods: {
			getSelectedUser(){
				this.selectedUser = uni.getStorageSync("selectedUser");
				if(this.selectedUser&&this.selectedUser.length>0){
					this.searchTerms.createBy = this.selectedUser[0].account;
				}
			},
			checkIp(){
				let ipAddress = uni.getStorageSync('ipaddress');
				if(ipAddress==""){
					uni.redirectTo({
						url:"../my/login"
					})
				}
				this.$request.setBaseUrl(ipAddress);
			},
			hideModal(e) {
				this.modelBottom = null
			},
			initLables() {
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
					}
				});
			},
			search() {
				this.jumpDetailPage("basicSearch");
			},
			InputFocus() {
				this.focus = true;
			},
			filter(e) {
				this.showRight = true;
			},
			closeDrawer() {
				this.showRight = false;
			},
			reset() {
				this.showRight = false;
			},
			highSearch() {
				if((this.startTime&&this.endTime)||(this.startTime==""&&this.endTime=="")){
					this.showRight = false;
					this.jumpDetailPage("highSearch");
				}else{
					//逻辑与PC端保持一致
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: "请填写开始时间和结束时间"
					});
					return;
				}
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
			endDateChange(e) {
				this.endTime = e.detail.value;
			},
			chooseUser(){
				uni.navigateTo({
					url: '../common/user_list?lastPage=HOME'
				});
			},
			parseDate(data) {
				if (data) {
					return $DataUtils.formatDate(new Date(data), "yyyy-MM-dd");
				}
				return "请选择";
			},
			initDict(code, arr) {
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
					}
					let item = {};
					item.key = "-1";
					item.value = "全部";
					item.checked = false;
					arr.splice(0,0,item);
				});
			},
			jumpDetailPage(searchType) {
				//将搜索条件存储到store中
				this.searchTerms.createTimeStr = this.startTime +"~"+this.endTime;
				this.searchTerms.createTimeStr=this.searchTerms.createTimeStr=="~"?"":this.searchTerms.createTimeStr;
				if(this.typeStatus!=-1){
					this.searchTerms.type = this.fileTypeArr[this.typeStatus].key;
				}
				if(this.labelStatus!=-1){
					this.searchTerms.label = this.labelsArr[this.labelStatus].id;
				}
				this.searchTerms.searchType = searchType;
				uni.setStorageSync("searchTerms", this.searchTerms);
				uni.navigateTo({
					url: "/pages/search/home_detail"
				})
				this.modelBottom = !this.modelBottom;
			},
			searchData(){
				if(this.searchText==""){
					return;
				}
				this.searchTerms.keyword = this.searchText;
				this.jumpDetailPage("basicSearch");
			}
		}
	}
</script>

<style>
	.page {
		height: 100vh;
	}

	.body-bg {
		display: flex;
		flex-direction: column;
		position: absolute;
		bottom: 0;
		top: var(--status-bar-height);
		left: 0;
		right: 0;
	}

	.img {
		height: 100px;
		width: 100%;
	}

	.search {
		font-size: 24upx;
		margin-left: 44upx;
		padding: 10upx;
		top: 480upx;
	}
	.img-layout{
		position: absolute;
		color: #2BC1FF;
		font-size: 42upx;
		margin-left: 70upx;
		padding-bottom: 8upx;
		padding-left: 10upx;
		top: 430upx;
	}
	.wq-item {
		border-top: 1upx solid #80808038;
		border-bottom: 1upx solid #80808038;
		margin-bottom: 16upx;
	}

	.search-btn {
		position: absolute;
		bottom: 0px;
		width: 100%;
	}

	.status_bar {
		height: var(--status-bar-height);
		width: 100%;
		background-color: white;
	}

	.top_view {
		height: var(--status-bar-height);
		width: 100%;
		position: fixed;
		background-color: white;
		top: 0;
		z-index: 999;
	}

	.wq-dialog {
		top: var(--status-bar-height);
	}

	.basis-lg {
		flex-basis: 76%;
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
		margin-bottom: calc(100upx + env(safe-area-inset-bottom) / 2);
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
	.bg-btn-color{
		    background-color: #81D3E0;
	}
	.uni-input-input {
	    font-size: 15px;
	}
	.uni-input-placeholder {
	    font-size: 12px;
	}
	.cu-bar .search-form {
	    line-height: 34px;
	    height: 34px;
	}
	.cu-bar .action>uni-text[class*="cuIcon-"], .cu-bar .action>uni-view[class*="cuIcon-"] {
	    font-size: 18px;
	}
	.search-input{
		border-radius: 10px;
		border: 1px solid gray;
		background-color: white;
	}
</style>
