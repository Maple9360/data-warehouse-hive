<el-upload
  class="upload"
  action="/api/upload"
  :show-file-list="false"
  :on-success="onSuccess"
  :before-upload="beforeUpload"
>
  <el-button slot="trigger" type="primary">选取文件</el-button>
  <div slot="tip" class="upload-tip">只能上传jpg/png文件，且不超过500kb</div>
  <div class="upload-list" slot-scope="{ fileList }">
    <el-upload-list :list-type="'picture-card'">
      <div v-for="(file, index) in fileList" :key="index" class="upload-item">
        <img :src="file.url" alt="" class="upload-img">
      </div>
    </el-upload-list>
  </div>
</el-upload>

<style>
.upload-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: 300px; /* 自定义宽度 */
}

.upload-item {
  width: 80px; /* 自定义宽度 */
  height: 80px; /* 自定义高度 */
  margin-right: 10px;
  margin-bottom: 10px;
  position: relative;
}

.upload-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
