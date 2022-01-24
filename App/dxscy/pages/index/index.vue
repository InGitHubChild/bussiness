<template>
	<view>
		<basics v-if="PageCur=='basics'"></basics>
		<components v-if="PageCur=='component'"></components>
		<plugin v-if="PageCur=='plugin'"></plugin>
		<search v-if="PageCur=='search' || PageCur=='voice'" :searchText="sendVoiceText"></search>
		<my v-if="PageCur=='my'"></my>
		<view class="cu-bar tabbar bg-white shadow foot">
			<view class="action" @click="NavChange" data-cur="search">
				<view class='cuIcon-cu-image'>
					<text :class="PageCur=='search'?'text-green cuIcon-searchlist':'cuIcon-searchlist'" style="font-size: 44upx;"></text>
				</view>
				<view :class="PageCur=='search'?'text-green':'text-gray'">检索</view>
			</view>
			<view class="action" @click="voiceClick" data-cur="voice">
				<view class='cuIcon-cu-image'>
					<text :class="PageCur=='voice'?'text-green cuIcon-voice':'text-gray cuIcon-voice'" style="font-size: 44upx;"></text>
				</view>
				<view :class="PageCur=='voice'?'text-green':'text-gray'">语音</view>
			</view>
			<view class="action" @click="NavChange" data-cur="my">
				<view class='cuIcon-cu-image'>
					<text :class="PageCur=='my'?'text-green cuIcon-my':'text-gray cuIcon-my'" style="font-size: 44upx;"></text>
				</view>
				<view :class="PageCur=='my'?'text-green':'text-gray'">我的</view>
			</view>
		</view>
		<view class="cu-modal bottom-modal" :class="showModal?'show':''" @click="hideModal">
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
	
	export default {
		data() {
		return {
				PageCur: 'search',
				showModal:false,
				voiceText:"",
				voiceColor:false,
				voicePath: '',
				sendVoiceText:"",
				isShowLoading:false
			}
		},
		watch:{
			showModal(val, oldVal) {
				if(val==false){
					recorderManager.stop();
				}
			}
		},
		onLoad() {
		    let self = this;
		    recorderManager.onStop(function (res) {
		        self.voicePath = res.tempFilePath;
				self.uploadAudio();
				self.isShowLoading = true;
				console.log("onStop");
		    });
		},
		methods: {
			NavChange: function(e) {
				this.PageCur = e.currentTarget.dataset.cur
			},
			voiceClick(){
				this.showModal = !this.showModal;
				this.voiceText = '';
				this.sendVoiceText = '';
			},
			hideModal(){
				this.showModal = false;
			},
			clearData(){
				this.voiceText="";
			},
			sendData(){
				this.showModal = false;
				this.sendVoiceText = this.voiceText;
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
