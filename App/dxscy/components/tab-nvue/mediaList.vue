<template>
	<view>
		<view class="list-cell" hover-class="uni-list-cell-hover" @click="bindClick">
			<view class="media-list">
				<view v-if="tabIndex==0||tabIndex==1||tabIndex==2||tabIndex==3||tabIndex==4||tabIndex==7" class="content">
					<view><rich-text class="article-title" :nodes="formateBasicTitle(options)"></rich-text></view>
					<rich-text class="content" :nodes="options.content"></rich-text>
					<rich-text v-if="options.remark!=''" class="remark" :nodes="options.remark"></rich-text>
					<view class="media-foot">
						<view class="media-info">
							<text class="info-text">{{formateType(options.sysDictItem)}}</text>
							<text class="info-text">{{formateDate(options.createDate)}}</text>
						</view>
					</view>
				</view>
				<!--图片-->
				<view v-if="tabIndex==5" class="content">
					<image :src="formateImgPath(options.filePath)" style="width: 100%;" mode="widthFix"></image>
					<rich-text v-if="options.remark!=''" class="remark" :nodes="options.remark"></rich-text>
					<view class="media-foot">
						<view class="media-info">
							<view><rich-text class="info-text" :nodes="options.name"></rich-text></view>
							<text class="info-text">{{formateType(options.sysDictItem)}}</text>
							<text class="info-text">{{formateDate(options.createDate)}}</text>
						</view>
					</view>
				</view>
				<!--视频-->
				<view v-if="tabIndex==6" class="content">
					<image :src="formateVideoImgPath(options.filePath)" style="width: 100%;" mode="widthFix"></image>
					<rich-text v-if="options.remark!=''" class="remark" :nodes="options.remark"></rich-text>
					<view class="media-foot">
						<view class="media-info">
							<view><rich-text class="info-text" :nodes="options.name"></rich-text></view>
							<text class="info-text">{{formateType(options.sysDictItem)}}</text>
							<text class="info-text">{{formateDate(options.createDate)}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import $DataUtils from '@/common/utils/dataUtils.js';
	export default {
		props: {
			options: {
				type: Object,
				default: function(e) {
					return {}
				}
			},
			tabIndex:{
				type:Number,
				default:function(e){
					return 0
				}
			}
		},
		data(){
			return {
			}
		},
		methods: {
			bindClick() {
				this.$emit('click');
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
			formateImgPath(filePath){
				let imgUrl = this.$request.config.baseUrl+"no/download?filePath="+filePath;
				return imgUrl;
			},
			formateVideoImgPath(filePath){
				filePath = filePath.substring(0,filePath.lastIndexOf("."))+".jpg";
				let imgUrl = this.$request.config.baseUrl+"no/download?filePath="+filePath;
				return imgUrl;
			}
		}
	}
</script>

<style>
	view {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
	}

	.list-cell {
		width: 750upx;
		padding: 0 30upx;
	}

	.uni-list-cell-hover {
		background-color: #eeeeee;
	}

	.media-list {
		flex: 1;
		flex-direction: column;
		border-bottom-width: 1upx;
		border-bottom-style: solid;
		border-bottom-color: #c8c7cc;
		padding: 20upx 0;
	}

	.media-image-right {
		flex-direction: row;
	}

	.media-image-left {
		flex-direction: row-reverse;
	}

	.media-title {
		flex: 1;
	}

	.media-title {
		lines: 3;
		text-overflow: ellipsis;
		font-size: 32upx;
		color: #555555;
	}

	.media-title2 {
		flex: 1;
		margin-top: 6upx;
		line-height: 40upx;
	}

	.image-section {
		margin-top: 20upx;
		flex-direction: row;
		justify-content: space-between;
	}

	.image-section-right {
		margin-top: 0upx;
		margin-left: 10upx;
		width: 225upx;
		height: 146upx;
	}

	.image-section-left {
		margin-top: 0upx;
		margin-right: 10upx;
		width: 225upx;
		height: 146upx;
	}

	.image-list1 {
		width: 690upx;
		height: 481upx;
	}

	.image-list2 {
		width: 225upx;
		height: 146upx;
	}

	.image-list3 {
		width: 225upx;
		height: 146upx;
	}

	.media-info {
		flex-direction: row;
	}

	.info-text {
		margin-right: 20upx;
		color: #999999;
		font-size: 24upx;
	}

	.media-foot {
		margin-top: 20upx;
		flex-direction: row;
		justify-content: space-between;
	}

	.max-close-view {
		align-items: center;
		justify-content: flex-end;
		flex-direction: row;
		height: 40upx;
		width: 80upx;
	}

	.close-view {
		border-style: solid;
		border-width: 1px;
		border-color: #999999;
		border-radius: 10upx;
		justify-content: center;
		height: 30upx;
		width: 40upx;
		line-height: 30upx;
	}

	.close {
		text-align: center;
		color: #999999;
		font-size: 28upx;
	}
	
	.page {
		height: 100vh;
	}
	.body-bg{
		background-color: white;
		height: 100%;
	}
	.nav .cu-item {
	    height: 34px;
	    line-height: 34px;
	}
	.type-font{
		font-size: 28upx;
	}
	.search{
		font-size: 24upx;
		margin-left: 10upx;
		padding: 10upx;
	}
	
	.article-title{
		font-weight: 700;
		font-size: 32upx;
	}
	.time,.type,.remark{
		font-size: 28upx;
		margin-right: 10upx;
	}
	.content{
		font-size: 30upx;
	}
	.wq-item{
		border-top: 1upx solid #80808038;
		border-bottom: 1upx solid #80808038;
		margin-bottom: 16upx;
	}
	.basis-lg {
	    flex-basis: 76%;
	}
	.cu-form-group {
	    min-height: 45px;
	}
	.search-btn{
		position: absolute;
		bottom: 0px;
		width: 100%;
	}
</style>
