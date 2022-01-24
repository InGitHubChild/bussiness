<template>
	<view style="height: 100%;">
		<view class="body-bg">
			<view class="cu-bar bg-orange-cu search">
				<view class="wq-back"><text class="cuIcon-back"></text></view>
			</view>
		</view>
		<view class="aaa">
			<video id="myVideo" style="width: 100%;" :src="videoUrl"
				 controls autoplay=true ></video>
		</view>
	</view>
    
</template>

<script>
	export default {
		data() {
			return {
				videoUrl:""
			}
		},
	    onLoad:function(option){
	    	//获取搜索的类型
			this.videoUrl = this.$request.config.baseUrl+"no/player?filePath="+option.filePath;
	    },
	    methods: {

	    }
	}
</script>
<style>
	.aaa{
		width: 100%;
		height: calc(100% - 100rpx);
	}
	
</style>