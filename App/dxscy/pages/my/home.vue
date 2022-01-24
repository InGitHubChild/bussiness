<template>
	<view class="body-bg">
		<view class="flex padding justify-start user-info-all">
			<view class="padding-sm margin-xs radius" @click="login">
				<view class="cu-avatar round xl round margin-left headshot">{{formateAvatar(user.userName)}}</view>
					<text class="text-df padding">{{formateName(user.userName)}}</text>
			</view>				
		</view>
		<view class="cu-list menu">
			<view v-if="!user.userId" class="cu-item arrow" @click="login">
				<view class="content">
					<text class="cuIcon-peoplefill text-green"></text>
					<text class="text-grey">登陆</text>
				</view>
			</view>
			<view v-else class="cu-item arrow" @click="exit">
				<view class="content">
					<text class="cuIcon-peoplefill text-red"></text>
					<text class="text-grey">退出</text>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				user: {
					userName: '', //用户/电话
					password: '', //密码
					userId:"",
					account:''
				}
			};
		},
		created() {
			this.init();
		},
		methods:{
			init(){
				let userData = uni.getStorageSync("user");
				this.user = userData;
			},
			exit() {
				uni.removeStorageSync('user');
				uni.reLaunch({
					url:'/pages/my/login'
				})
			},
			login(){
				//判断未登陆就跳到登陆页面
				if(this.user.id){
					return;
				}
				uni.reLaunch({
					url:'/pages/my/login'
				})
			},
			formateName(name){
				if(!name){
					return "未登陆";
				}
				return name;
			},
			formateAvatar(name){
				if(!name){
					return "";
				}
				var lastName =  name.substring(name.length-1,name.length);
				return lastName;
			}
		}
	}
</script>

<style>
	.mypage-head {
		margin-bottom: 50px;
	}
	.f-login-location{
		align-items: center;
		display: flex;
	}
	.body-bg{
		display: flex;
		flex-direction: column;
		background-color: #f1f1f1;
		position: absolute;
		bottom: 0;
		top: var(--status-bar-height);
		left: 0;
		right: 0;
	}
	.cu-avatar{
		background-color: #6e995d;
	}
	.cu-list.menu>.cu-item{
		background-color: unset;
	}
</style>
