<template>
  <!-- <div class="formStyle"> -->
  <el-row class="formStyle">
    <el-col :lg="8" :md="8" :sm="24"
      ><el-form
        ref="ruleFormRef"
        :label-position="labelPosition"
        :model="ruleForm"
        :rules="rules"
        label-width="100px"
        class="demo-ruleForm"
        :size="formSize"
        style="width: 35vw"
        status-icon
      >
        <el-form-item label="Work ID" prop="empno">
          <el-input
            style="width: 10vw; max-width: 300px"
            v-model="ruleForm.empno"
            placeholder="F123456"
          />
        </el-form-item>
        <el-form-item label="Logon name" prop="username">
          <el-input
            style="width: 10vw; max-width: 300px"
            v-model="ruleForm.username"
            placeholder="張三"
          />
        </el-form-item>
        <el-form-item label="System role" prop="role">
          <el-select
            popper-class="popperSelect"
            style="width: 10vw; max-width: 300px"
            v-model="ruleForm.role"
            placeholder="role"
          >
            <el-option
              v-for="item in formOptions.system_role"
              :key="item"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
          <div
            @click="showRolelist"
            style="display: flex"
            title="click to authority list"
          >
            <svg-icon className="iconMenu" iconName="icon-details"></svg-icon>
          </div>
          <el-dialog
            v-model="showRoleFlag"
            title="Role list"
            style="width: 800px"
          >
            <table>
              <tr v-for="(obj, index) in tableData" :key="index">
                <td style="color: #40acb8">{{ obj.role }}</td>
                <td>{{ obj.describe }}</td>
              </tr>
            </table>
          </el-dialog>
        </el-form-item>
        <el-form-item label="Site" prop="site">
          <el-select
            popper-class="popperSelect"
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.site"
            multiple
            placeholder="可多選"
          >
            <el-option
              v-for="item in formOptions.site"
              :key="item"
              :disabled="exceptAll(ruleForm.site, item)"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="MFG" prop="mfg">
          <el-select
            popper-class="popperSelect"
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.mfg"
            multiple
            placeholder="可多選"
          >
            <el-option
              v-for="item in formOptions.mfg"
              :disabled="exceptAll(ruleForm.mfg, item)"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Area" prop="area">
          <el-select
            popper-class="popperSelect"
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.area"
            multiple
            placeholder="可多選"
          >
            <el-option
              v-for="item in formOptions.area"
              :key="item"
              :disabled="exceptAll(ruleForm.area, item)"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Building" prop="building">
          <el-select
            popper-class="popperSelect"
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.building"
            multiple
            placeholder="可多選"
          >
            <el-option
              v-for="item in formOptions.building"
              :key="item"
              :disabled="exceptAll(ruleForm.building, item)"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Floor" prop="floor">
          <el-select
            popper-class="popperSelect"
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.floor"
            multiple
            placeholder="可多選"
          >
            <el-option
              v-for="item in formOptions.floor"
              :key="item"
              :label="item"
              :disabled="exceptAll(ruleForm.floor, item)"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.email"
            placeholder="xxxxxxxxx@mail.foxconn.com"
          />
        </el-form-item>
        <el-form-item label="Ext" prop="ext">
          <el-input
            style="width: 15vw; max-width: 250px"
            v-model="ruleForm.ext"
            placeholder="000-00000"
          />
        </el-form-item>
        <el-form-item>
          <el-button class="buttonSubmit" @click="submitForm(ruleFormRef)">
            {{ $t('authority.submit') }}
          </el-button>
          <el-button class="buttonCancel" @click="resetForm(ruleFormRef)">{{
            $t('authority.reset')
          }}</el-button>
        </el-form-item>
      </el-form></el-col
    >
    <el-col :lg="16" ::md="8" :sm="24"><sign-up :url="url"></sign-up></el-col>
  </el-row>

  <!-- </div> -->
</template>

<script setup lang="ts">
import { computed, reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
// api
import { getDropdown, postPermissionApply } from '@/api/authority.ts'
// import { ElMessage, ElLoading } from 'element-plus'
import { responseHandle } from '@/utils/message.ts'
import signUp from './signUp.vue'

// ================ form 设置 =========================
const labelPosition = ref('right') // label对齐方式
const formSize = ref('default') // default small large
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  empno: '', // name
  username: '', // region
  role: '', // count
  site: [], //
  mfg: [], //
  area: [], //
  building: [],
  floor: [],
  email: '',
  ext: '',
})

const rules = reactive<FormRules>({
  empno: [
    {
      type: 'string',
      required: true,
      message: 'NULL',
      trigger: 'blur',
    },
    {
      min: 3,
      max: 10,
      message: 'length error',
      trigger: 'blur',
    },
  ],
  username: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  role: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  // date1: [
  //   {
  //     type: 'date',
  //     required: true,
  //     message: 'Please pick a date',
  //     trigger: 'change',
  //   },
  // ],
  // date2: [
  //   {
  //     type: 'date',
  //     required: true,
  //     message: 'Please pick a time',
  //     trigger: 'change',
  //   },
  // ],
  site: [
    {
      // type: 'array',
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  mfg: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  area: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  building: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  floor: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  email: [
    {
      required: true,
      message: 'NULL',
      trigger: 'change',
    },
  ],
  ext: [
    {
      required: true,
      message: 'The text cannot be blank or NULL.',
      trigger: 'change',
    },
  ],
})

// ================= form校验提交 =======================
// 选项选了ALL后其他不可以点击
const exceptAll = (arrSelected: Array, currentOption: string): boolean => {
  if (arrSelected.length === 0) {
    // 还没选择时，都可以点
    return false
  }
  if (arrSelected.indexOf('ALL') === -1) {
    // 当前没选all,可以选其他 =》 不空时，要禁用all
    if (currentOption === 'ALL') return true // 禁用all选项
    return false
  } else {
    // 当前选了all，不可以选其他
    if (currentOption === 'ALL') return false
    return true
  }
}
const submitForm = async (formEl: FormInstance | undefined) => {
  // console.log(formEl)
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    // console.log(formEl, valid)
    if (valid) {
      console.log('submit!')
      const obj = transferForm(ruleForm)
      // console.log(obj)
      // ======================== 注册 =======================
      postPermissionApply(obj).then((res) => {
        console.log(res)
        responseHandle(res, true)
        // Submitted successfully!
      })
    } else {
      // const obj = transferForm(ruleForm)
      // console.log(obj)
      console.log('error submit!', fields)
    }
  })
}
let formOptions = reactive({
  logon_name: null,
  system_role: ['工務', '采購'],
  site: ['FOL'],
  mfg: ['MFGVI', 'MFGVII', 'MFGVIII', '工務'],
  area: ['D區', 'E區', 'F區'],
  building: ['D10', 'D9  ', 'E5', 'E6', 'F21'],
  floor: ['1.5F', '2F', '3F', '4F'],
  email: null,
  ext: null,
})
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

// ==================== getData =====================
const tableData = ref()
const getData = () => {
  getDropdown().then((res) => {
    console.log(res)
    // 选项对接
    formOptions = { ...formOptions, ...res.data }
    url.value = res.data.url
    tableData.value = res.data.tableData
  })
}
getData()
// ==================== 批量注册模板 ================
const url = ref()
// ===================== 把ruleForm的格式转化成字符串 ====================
const transferForm = (obj: Object) => {
  const target = {}
  for (const i in obj) {
    // console.log(i)
    if (obj[i] instanceof Array) {
      // console.log(i, obj[i])
      target[i] = ''
      obj[i].map((item) => {
        target[i] += '/' + item
        return item
      })
      // 去掉target前面的/
    } else {
      target[i] = obj[i]
      // console.log(target[i])
    }
  }
  // console.log(obj)
  return target
}

// ==================== showRolelist ===================
const showRoleFlag = ref(false)
const showRolelist = () => {
  showRoleFlag.value = true
}
</script>

<style lang="scss" scoped>
// form样式
.formStyle {
  // display: flex;
  // flex-wrap: wrap;
  height: 900px;
  // justify-content: flex-start;
  :deep() .el-input__wrapper {
    background-color: transparent !important;
    &.is-focus {
      box-shadow: 0 0 0 1px $font-color;
    }
  }
  :deep() .el-form-item__label {
    color: $font-gray;
  }
  :deep() .el-input__inner {
    color: rgb(255, 255, 255);
    &::placeholder {
      color: #888 !important;
    }
  }
  :deep() .el-select .el-input.is-focus .el-input__wrapper {
    //focus select框的颜色
    box-shadow: 0 0 0 1px $font-color !important;
  }
  :deep() .el-select .el-input .is-focus.el-input__wrapper {
    //focus select框的颜色
    box-shadow: 0 0 0 1px $font-color !important;
  }
  :deep().el-select__tags .el-tag--info {
    // 选择框样式
    background-color: rgb(101, 137, 148);
    color: #fff;
  }
  :deep() .el-tag .el-tag__close {
    // 删除icon样式
    color: #fff;
  }
}

// 选择框选中的option样式
.el-select-dropdown.is-multiple .el-select-dropdown__item.selected {
  color: $font-color;
}
.el-select-dropdown__item.is-disabled {
  color: #f56c5b;
}
.el-select-dropdown__item.selected {
  color: $font-color;
}
// 按钮样式
.buttonSubmit {
  color: $font-gray;
  background-size: 100% 100%;
  background-image: url('/src/assets/total/button1.png');
  background-color: transparent;
  border: none;
  &:hover {
    background-color: #ffffff80;
    color: $font-color;
  }
}
.buttonCancel {
  color: #f56c5b;
  // background-size: 100% 100%;
  // background-image: url('/src/assets/total/button1.png');
  background-color: transparent;
  border: 1.5px solid #f56c5b;
  border-radius: 5px;
  &:hover {
    background-color: #ffffff80;
    color: red;
    border: 1.5px solid red;
  }
}

// icon
// icon
.iconMenu {
  margin: 5px;
  color: $font-gray;
  font-size: 24px;
  // height: 20px;
  margin: 0px 5px;
  &:hover {
    color: $font-color;
    cursor: pointer;
  }
}
</style>

<style lang="scss">
// popper: select下拉菜单类型属性的样式
.popperSelect {
  .el-select-dropdown__wrap {
    // 下拉弹窗
    background: rgb(204, 204, 204) !important;
  }
}
</style>
