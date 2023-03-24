
      <!-- 卡片试图区 -->
      <el-card class="mainDiv">
        <!-- 搜索与添加区域 -->
        <el-row :gutter="24">
          <el-col :span="6" :offset="14">
            <el-input v-model="queryInfo.query" placeholder="Please input" @clear="getUserList" clearable
              style="width: 300px">
              <template #prepend>
                <el-select v-model="select" placeholder="Select" style="width: 60px">
                  <el-option label="Restaurant" value="1" />
                  <el-option label="Order No." value="2" />
                  <el-option label="Tel" value="3" />
                </el-select>
              </template>
              <template #append>
                <!-- <el-button :icon="Search" /> -->
                <el-button>
                  <el-icon>
                    <Search />
                  </el-icon>
                </el-button>
  
              </template>
            </el-input>
          </el-col>
          <el-col :span="2" :offset="2">
            <el-button @click="addDialogVisible = true"><el-icon>
                <Plus />
              </el-icon>New</el-button>
          </el-col>
        </el-row>
  
  
        <!-- 用户列表区 -->
        <el-table :data="userList" stripe border :row-class-name="tableRowClassName">
          <el-table-column label="No." type="index"></el-table-column>
          <el-table-column label="Site"></el-table-column>
          <el-table-column label="證書編號"></el-table-column>
          <el-table-column label="證書標準"></el-table-column>
          <el-table-column label="證書附件" prop="username"></el-table-column>
          <template>
            <!-- 上传文件 -->
            <el-upload class="upload-demo" drag action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
              multiple>
              <el-icon class="el-icon--upload"><upload-filled /></el-icon>
              <div class="el-upload__text">
                Drop file here or <em>click to upload</em>
              </div>
              <template #tip>
                <div class="el-upload__tip">
                  jpg/png files with a size less than 500kb
                </div>
              </template>
            </el-upload>
          </template>
          <el-table-column label="發證日期" prop="email"></el-table-column>
          <el-table-column label="失效日期" prop="mobile"></el-table-column>
          <el-table-column label="狀態">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.mg_state" @change="userStateChanged(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="上傳人" prop="role_name"></el-table-column>
          <el-table-column label="編輯" width="180px">
            <template slot-scope="scope">
              <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
              <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserById(scope.row.id)"></el-button>
              <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
                <el-button type="warning" icon="el-icon-setting" size="mini" @click="setRole(scope.row)"></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页区域 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pagesize"
          layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
      </el-card>
