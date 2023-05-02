import { createRouter, createWebHashHistory } from 'vue-router'
import Layout from '../layout/index.vue'

/**
 * hidden: true               if set true, item will not show in the sidebar(default is false) 不会出现在侧边栏
 * alwaysShow: true           在侧边栏上，当子菜单数>1时，不设为true也会显示根菜单；子菜单数=1时，不设不会显示根菜单，直接显示子菜单；
 * redirect: noRedirect       *未启用
 * name: 'router-name'
 * meta: {
 *  roles: ['admin','editor']    control the page roles (you can set multiple roles) *未启用
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false) *未启用
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set    对应高亮
 * }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/',
    redirect: '/dashboard/index',
    hidden: true, // 是否隐藏
    meta: {},
    // component:
  },
  {
    path: '/login',
    component: () => import('../views/login/index.vue'),
  },
  {
    path: '/authority',
    component: () => import('../views/authority/index.vue'),
  },
  {
    path: '/home',
    redirect: '/dashboard/index',
    meta: {
      title: 'Home',
    },
    component: Layout,
  },
  {
    path: '/alarmCenter',
    redirect: '/alarmCenter/index',
    component: Layout,
    meta: {
      title: 'Alarm center',
    },
    children: [
      {
        path: 'index',
        meta: {
          title: 'Alarm Center',
        },
        component: () => import('../views/alarmCenter/index.vue'),
      }
    ],
  },
  {
    path: '/dataCenter',
    redirect: '/dataCenter/index',
    component: Layout,
    meta: {},
    children: [
      {
        path: 'index',
        meta: {
          title: 'Data Center',
        },
        component: () => import('../views/dataCenter/index.vue'),
      },
    ],
  },
  {
    path: '/groudingMgmt',
    redirect: '/groudingMgmt/index',
    // alwaysShow: true,
    component: Layout,
    meta: {
      title: 'Grouding Mgmt',
    },
    children: [
      {
        path: 'index',
        meta: {
          title: '楼栋接地',
        },
        component: () => import('../views/groudingMgmt/index.vue'),
      },
      {
        path: 'index2',
        meta: {
          title: '楼层接地',
        },
        component: () => import('../views/groudingMgmt/index.vue'),
      },
      {
        path: 'index3',
        meta: {
          title: '线体接地',
        },
        component: () => import('../views/groudingMgmt/index.vue'),
      },
      {
        path: 'index4',
        meta: {
          title: '工站接地',
        },
        component: () => import('../views/groudingMgmt/index.vue'),
      },
    ],
  },
  {
    path: '/personalMgmt',
    redirect: '/personalMgmt/index',
    component: Layout,
    meta: {
      title: 'Personal Mgmt',
    },
    children: [
      {
        path: 'index',
        meta: {
          title: '人员接地',
        },
        component: () => import('../views/personalMgmt/index.vue'),
      },
      {
        path: 'index2',
        meta: {
          title: '人体行走',
        },
        component: () => import('../views/personalMgmt/index.vue'),
      },
    ],
  },
  // {
  //   path: '/dashboard',
  //   redirect: '/dashboard/index',
  //   component: Layout,
  //   meta: { title: 'title' },
  // },
  {
    path: '/dashboard',
    redirect: '/dashboard/index',
    meta: {
      title: 'title',
    },
    component: Layout,
    children: [
      {
        path: 'index',
        meta: {
          title: 'title',
        },
        component: () => import('../views/dashboard/index.vue'),
      },
    ],
  },
  {
    path: '/esdsMgmt',
    redirect: '/esdsMgmt/index1',
    component: Layout,
    meta: {
      location: 'right',
      title: 'ESDS Mgmt',
    },
    children: [
      {
        path: 'index1',
        meta: { title: '周期性检验物品' },
        component: () => import('../views/esdsMgmt/index.vue'),
      },
      {
        path: 'index2',
        meta: { title: '防静电物品进料检验' },
        component: () => import('../views/esdsMgmt/index.vue'),
      },
      {
        path: 'index3',
        meta: { title: '防静电物品认证认可' },
        component: () => import('../views/esdsMgmt/index.vue'),
      },
    ],
  },

  {
    path: '/eosMgmt',
    redirect: '/eosMgmt/index',
    component: Layout,
    meta: { location: 'right' },
    children: [
      {
        path: 'index',
        meta: {
          title: 'EOS Mgmt',
        },
        component: () => import('../views/eosMgmt/index.vue'),
      },
    ],
  },
  {
    path: '/esdAudit',
    redirect: '/esdAudit/index',
    component: Layout,
    meta: { title: 'ESD Audit', location: 'right' },
    children: [
      {
        path: 'index',
        meta: { title: 'ESD Audit' },
        component: () => import('../views/esdAudit/index.vue'),
      },
    ],
  },
  {
    path: '/sharing',
    redirect: '/sharing/index',
    component: Layout,
    meta: { location: 'right' },
    children: [
      {
        path: 'index',
        meta: { title: 'Sharing' },
        component: () => import('../views/sharing/index.vue'),
      },
    ],
  },
  // {
  //   path: '/test',
  //   redirect: '/test/index',
  //   // component: Layout,
  //   meta: { location: 'right' },
  //   children: [
  //     {
  //       path: 'index',
  //       meta: { title: 'test' },
  //       component: () => import('../views/test/index.vue'),
  //     },
  //   ],
  // },
  {
    path: '/setting',
    redirect: '/setting/index',
    meta: { title: 'setting' },
    children: [
      {
        path: 'index',
        meta: { title: 'Authority' },
        component: () => import('../views/authority/index.vue'),
      },
      {
        path: 'index2',
        meta: {
          title: 'People Flow Monitoring',
        },
        component: () => import('../views/peopleFlowMonitoring/index.vue'),
      }
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes,
})
export default router
