<template>
	<view>
		<cu-custom bgColor="white" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">查看图片</block>
		</cu-custom>
		<image :src="imgUrl" mode="widthFix"></image>
		<view v-if="imgName!=''" class="padding flex flex-wrap justify-end padding-lr-xl"><text>文件名：{{imgName}}</text></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imgurl:'',
				imgName:''
			};
		},
		onLoad(option){
			this.imgUrl = option.imgUrl;
			let name = option.name;
			name = name.replace(new RegExp('<span style="color:red">', "gm"), '')
			name = name.replace(new RegExp('</span>', "gm"), '')
			this.imgName = name;
		},
		methods: {
		
			
		}
	}
</script>
<style>
	
</style>

