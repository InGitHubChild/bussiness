<template>
	<view>
		<!-- <view class="img-layout">
			<image class="img" src="../../static/img/logo.png" alt=""></image>
		</view> -->
		<view class="img-layout">
			医疗智能检索
		</view>
		<view class="item-layout">
			<view class="content">
				<!-- 主体表单 -->
				<view class="main">
					<wInput v-if="showIp" v-model="ipAddress" type="text" maxlength="200" placeholder="输入服务器地址：端口号"></wInput>
					<wInput v-model="username" type="text" maxlength="11" placeholder="用户名/电话"></wInput>
					<wInput v-model="password" type="password" maxlength="11" placeholder="密码"></wInput>
				</view>
				<wButton text="登 录" :rotate="isRotate" :style="{color:bgColor}" @click.native="startLogin()"></wButton>
			</view>
		</view>
	</view>
</template>
<script>
	var _this;
	//登陆页面组件
	import wInput from '../../components/watch-login/watch-input.vue' //input
	import wButton from '../../components/watch-login/watch-button.vue' //button

	export default {
		data() {
			return {
				//logo图片 base64
				logoImage: '',
				username: '', //用户/电话
				password: '', //密码
				ipAddress:'',
				isRotate: false, //是否加载旋转
				isLoginStatus:false,
				bgColor:'#267CC7',
				showIp:false
			};
		},
		components: {
			wInput,
			wButton,
		},
		onLoad() {
			this.isLogin();
		},
		onShow() {
			this.fillData();
		},
		mounted() {
			_this = this;
		},
		methods: {
			fillData(){
				let ipaddress = uni.getStorageSync("ipaddress");
				let user = uni.getStorageSync('user');
				if(ipaddress&&user.account){
					this.ipAddress = ipaddress;
					this.showIp = false;
				}else{
					this.showIp = true;
					this.ipAddress = ipaddress;
				}
			},
			isLogin() {
				//判断缓存中是否登录过，直接登录
				try {
					let user = uni.getStorageSync('user');
					this.$request.post("login",{account:user.account,password:user.password,"isApp":"true"})
					 .then(res => {
						 let responsedata = res.data;
						if(responsedata.code=="Success"){
							//登陆成功
							let user = res.data.data;
							this.$request.setAuthorization(user.token);
							this.isLoginStatus = true;
							uni.reLaunch({
								url: '/pages/index/index'
							});
						}else{
							this.isLoginStatus = false;
						}
					})
				} catch (e) {
					// error
				}
			},
			showToast(msg){
				uni.showToast({
					icon: 'none',
					position: 'bottom',
					title: msg
				});
			},
			startLogin() {
				/* if(this.ipAddress==""||this.ipAddress.indexOf(":")==-1){
					this.showToast('请填写正确的IP地址');
					return;
				} */
				/* let ipAndPortArr = this.ipAddress.split(":");
				let ip = ipAndPortArr[0];
				let port = ipAndPortArr[1];
				let  IP = "^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$";
				let patt=new RegExp(IP); 
				if(!patt.test(ip)){
					this.showToast('请填写正确的IP地址');
					return;
				} */
				this.$request.setBaseUrl(this.ipAddress);
				
				//登录
				if (this.isRotate) {
					//判断是否加载中，避免重复点击请求
					return false;
				}
				if (this.username.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '用户名不能为空'
					});
					return;
				}
				if (this.password.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '密码不能为空'	
					});
					return;
				}
				uni.showLoading({
					title: '登录中'
				});
				
				this.$request.post("login",{account:this.username,password:this.password,"isApp":true})
				 .then(res => {
					 let user = res.data.data;
					 let responsedata = res.data;
					 //登陆成功
					 if(responsedata.code=="Success"){
						 let userdata={
						 	"account":user.account,
						 	"password":this.password,
						 	"userName":user.userName,
						 	"token":user.token,
						 	"userId":user.userId
						 }
						 this.$request.setAuthorization(user.token);
						 //存储IP地址到storge中
						 uni.setStorageSync("ipaddress", this.ipAddress);
						 uni.setStorageSync('user', userdata); //存入缓存
					
						 uni.showToast({
						 	icon: 'success',
						 	position: 'bottom',
						 	title: '登录成功'
						 });
						 uni.reLaunch({
						 	url: '/pages/index/index'
						 });
					 }else{
						 let msg = responsedata.msg;
						 uni.showToast({
						 	icon: 'error',
						 	position: 'bottom',
						 	title: msg
						 });
					 }
				}).catch(err => {
					uni.hideLoading();
					this.showToast('请填写正确的服务器地址');
				}) 
			}
		}
	}
</script>

<style>
	@import url("../../components/watch-login/css/icon.css");
	.item-layout{
		left: calc(50% - 296upx);
		top: 35%;
		position: absolute;
	}
	.img-layout{
		top: 28%;
		position: absolute;
		width: 100%;
		color: #267CC7;
		font-weight: 600;
		text-align: center;
		font-size: 36px;
	}
	.img {
		height: 100px;
		width: 100%;
	}
</style>
