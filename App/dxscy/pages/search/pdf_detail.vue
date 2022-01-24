<template>
	<view>
		<cu-custom bgColor="bg-orange-cu">
			<block slot="backText" >返回</block>
		</cu-custom>
		<web-view :src="url"></web-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				url:''
			}
		},
		onLoad(option) {
			let filePath = option.filePath.substring(0,option.filePath.lastIndexOf("."))+".pdf";
			// var pdfUrl = 'https://axhl-test-1252661357.cos.ap-shanghai.myqcloud.com/pdf/1536742627489-310270d9-4e12-2640-8cb6-cfc07a6f6d1b.pdf';
			var keyword = option.keyword;
			var content = option.content;
			var searchType = option.searchType;
			var fileId = option.fileId;
			var allFieldSearch = false;
			var searchSentence = "";
			var search = "";
			//判断是否需要全句子检索
			if(keyword!=""&&searchType=="basicSearch"){
				allFieldSearch = true;
				searchSentence = keyword;
			}
			if(content!=""&&searchType=="highSearch"){
				allFieldSearch = true;
				searchSentence = content;
			}
			var markUrl = this.$request.config.baseUrl+"attachment/markUsefulFile";
			var pdfUrl = this.$request.config.baseUrl+"no/down?filePath="+filePath+"&search="+keyword+"&allFieldSearch="+allFieldSearch+"&searchSentence="+searchSentence+"&fileId="+fileId+"&markUrl="+markUrl;
				this.url = "/hybrid/html/js/pdf-js/web/viewer.html?file=" + encodeURIComponent(pdfUrl);
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
