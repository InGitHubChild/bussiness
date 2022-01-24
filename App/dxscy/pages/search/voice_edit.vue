<template>
	<view>
		<cu-custom bgColor="white" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">编辑文字</block>
		</cu-custom>
		<view class="textarea-box">
			<textarea class="fy-textarea" auto-height  :value="searchText" @input="changeSearchText"/>
		</view>
		<view class="padding bottom">
			<view class="padding flex flex-wrap justify-end">
				<view class="modal-font" @tap.stop="sendData">发送</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchText:''
			};
		},
		onLoad(){
			this.searchText = uni.getStorageSync('searchText');
		},
		methods: { 
			changeSearchText: function (e) {
				this.searchText = e.detail.value;
			},
			sendData(){
				let  searchTermsTemp=  {
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
					fileType:'-1',//-1:全部
					searchType:'',
					pageSize:10,
					app:true
				}
				searchTermsTemp.keyword = this.searchText;
				searchTermsTemp.basicSearch = "basicSearch";
				uni.setStorageSync("searchTerms", searchTermsTemp);
				uni.navigateTo({
					url: "/pages/search/home_detail"
				})
			}
		}
	}
</script>
<style>
	.fy-textarea {
		width: unset;
		min-height: 500upx;
		height: 150px;
		display: block;
		position: relative;
		font-size: 16px;
		line-height: normal;
	}
	.textarea-box{
		padding:32upx;
	}
	.bottom{
		position: fixed;
		width: 100%;
		bottom: 0;
	}
</style>

