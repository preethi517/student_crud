import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { StudentComponent } from './components/student/student.component';

export const routes: Routes = [
    {
        path:'',
        component:HomeComponent,
        children:[
            {path:'',redirectTo:'student',pathMatch:'full'},
            {path:'student',component:StudentComponent}
        ]
    },
    {path:'**',component:PageNotFoundComponent}
];
