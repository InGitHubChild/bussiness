<template>
	<view>
		<cu-custom bgColor="bg-color" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">
				选择用户
			</block>
			<block slot="right">
				<view class="finish" @click="chooseOk">完成</view>
			</block>
		</cu-custom>
		<view class="cu-bar bg-color search-box" :style="{'top':CustomBar+'px'}">
			<view class="search-form radius">
				<text class="cuIcon-search"></text>
				<input @focus="InputFocus" @blur="InputBlur" :adjust-position="false" type="text" placeholder="搜索用户" confirm-type="search" @confirm="search" v-model="keyWord"></input>
			</view>
		</view>
		<view style="height:45px;"></view>
		<view>
			<scroll-view @scrolltolower="scrolldown" style="width: 100%;" :style="[{top:CustomBar+'px',height:'calc(100vh - ' + CustomBar + 'px - 45px)'}]" scroll-y="true">
				<view class="cu-list menu">
					<view class="cu-item" v-for="(item,index) in users" :key="index" @click="isChoose(index)">
						<view class="content padding-tb-sm">
							<view><text class="text-blue margin-right-xs"></text> {{item.name}}</view>
							<view class="text-gray text-sm"><text class="margin-right-xs"></text>{{item.account}}</view>
						</view>
						<view class="action" style="font-size: 20px;">
							<view v-if="item.isChoose==true">
								<uni-icons type="checkbox-filled" color="#a5cc09" size="23"></uni-icons>
							</view>
							<view v-if="item.isChoose==null||item.isChoose==false">
								<text class="lg text-gray cuIcon-round"></text>
							</view>
						</view>
					</view>
				</view>
				<view class="loading-more">
					<text class="loading-more-text">{{loadingText}}</text>
				</view>
			</scroll-view>
		</view>
	 </view>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				pageNo:1,
				pageSize:10,
				keyWord:'',
				users: [],
				loadingText:"加载更多",
				userId:"",
				lastPage:''
			}
		},
		onLoad:function(option){
			this.lastPage = option.lastPage;
		},
		onShow:function(){
			this.$request.get("users/pagedListNoOrg",{
			})
			 .then(res => {
				 let respData = res.data;
				 let resultData = res.data.data;
				 if (respData.msg == "Success") {
				 	this.users = resultData;
					if(this.users.length==0){
						this.loadingText = "没有数据";
					}else if(this.users.length<10){
						this.loadingText = "没有更多";
					}
				 }
			});
		},
		methods: {
			//是否弹出软件盘
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			//是否获取焦点
			InputBlur(e) {
				this.InputBottom = 0
			},
			scrolldown(){
				if(this.loadingText != "没有更多"){
					this.pageNo = this.pageNo+1;
					this.loadingText = "加载中";
					this.$request.get("users/pagedListNoOrg", {
						keyWord:this.keyWord,
						pageSize:this.pageSize,
						pageNo:this.pageNo
					})
					 .then(res => {
						 let responsedata = res.data;
						 //登陆成功
						if(responsedata.code=="Success"){
							for(let i=0;i<responsedata.data.length;i++){
								this.users.push(responsedata.data[i]);
							}
							if(responsedata.data.length != 10){
								this.loadingText = "没有更多";
							}else{
								this.loadingText = "加载更多";
							}
						}
					});
				}
			},
			isChoose(index) {
				for(let i = 0;i<this.users.length;i++){
				  if(i==index){
					  continue;
				  }
				  if(this.users[i].isChoose==true){
					  this.users[i].isChoose = false;
					 let dataTemp = this.users[i];
					 this.users.splice(i,1);
					 this.users.splice(i,0,dataTemp);
				  }
				}
				this.users[index].isChoose = !this.users[index].isChoose;
				if(this.users[index].isChoose){
					this.userId = this.users[index].id;
				}else{
					this.userId ="";
				}
				let data = this.users[index];
				this.users.splice(index,1);
				this.users.splice(index,0,data);
			},
			chooseOk(){
				//转移客户
				if(this.userId==""){
					uni.showToast({
						title: "请选择用户",
						icon: 'none'
					})
					return;
				}
				for(let i = 0;i<this.users.length;i++){
				  if(this.users[i].id==this.userId){
					let selectedUser = [];
					selectedUser.push(this.users[i]);
					uni.setStorageSync("selectedUser",selectedUser);
					if(this.lastPage=="HOME"){
						uni.$emit('getSelectedUser1');
					}else if(this.lastPage=="HOME_DETAIL"){
						uni.$emit('getSelectedUser2');
					}
					uni.navigateBack({
						delta: 1
					});
				  }
				}
			},
			search:function(){
				this.loadingText = "加载中";
				//获取协作人显示
				this.$request.get("users/pagedListNoOrg", {
					keyWord:this.keyWord
				}).then(res => {
					let respData = res.data;
					let resultData = res.data.data;
					this.users = resultData;
					if(this.users!=null&&this.users.length<=10){
						this.loadingText = "没有更多了";
					}else if(this.users!=null&&this.users.length>10){
						this.loadingText = "加载更多";
					}
				})
			}
		}
	}
</script>

<style>
	.back-icon{
		padding-left:10rpx ;
	}
	.loading-more {
	    align-items: center;
	    justify-content: center;
	    padding-top: 10px;
	    padding-bottom: 10px;
	    text-align: center;
	}
	
	.loading-more-text {
	    font-size: 28upx;
	    color: #999;
	}
	.search-box{
		position: fixed;
		width: 100%;
	}
	.finish{
		padding-right: 20upx;
	}
</style>