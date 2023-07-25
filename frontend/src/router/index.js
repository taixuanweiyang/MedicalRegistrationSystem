import { createRouter, createWebHistory } from 'vue-router';
import Base from '@/views/Base.vue';
const routes= [
    {
        path: '/',
        name: 'base',
        component: Base,
        children:[
            {
                path: '/',
                name: 'index',
                component: () => import('@/views/Index.vue')
            },
            {
                path: 'register',
                name: 'register',
                component: () => import('@/views/Register.vue')
            },
            {
                path: 'doctors',
                name: 'doctors',
                component: () => import('@/views/doctors/Base.vue'),
                children: [
                    {
                        path: '',
                        name: 'doctors',
                        component: () => import('@/views/doctors/Index.vue'),
                    },
                    {
                        path: 'appointment',
                        name: 'doctors_appointment',
                        component: () => import('@/views/doctors/Appointment.vue')
                    },
                    {
                        path: 'record',
                        name: 'doctors_record',
                        component: () => import('@/views/doctors/Record.vue')
                    },
                    {
                        path: 'medicine',
                        name: 'doctors_medicine',
                        component: () => import('@/views/doctors/Medicine.vue')
                    },
                    {
                        path: 'addInfo',
                        name: 'doctors_addInfo',
                        component: () => import('@/views/doctors/AddInfo.vue')
                    },
                ]
            },
            {
                path: 'patients',
                name: 'patients',
                component: () => import('@/views/patients/Base.vue'),
                children: [
                    {
                        path: '',
                        name: 'patients',
                        component: () => import('@/views/patients/Index.vue'),
                    },
                    {
                        path: 'center',
                        name: 'patients_center',
                        component: () => import('@/views/patients/Layout/Base.vue'),
                        children:[
                            {
                                path: '',
                                name: 'patients_center',
                                component: () => import('@/views/patients/Layout/Index.vue')
                            },
                            {
                                path: 'medicine',
                                name: 'patients_medicine',
                                component: () => import('@/views/patients/Layout/Medicine.vue')
                            },
                            {
                                path: 'appointment',
                                name: 'patients_appointment',
                                component: () => import('@/views/patients/Layout/Appointment.vue')
                            },
                        ]
                    },
                    
                ]
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
