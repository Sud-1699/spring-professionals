import { NgModule } from '@angular/core';

import { NzTableModule } from 'ng-zorro-antd/table';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzNotificationModule } from 'ng-zorro-antd/notification';

@NgModule({
    exports: [
        NzTableModule,
        NzAvatarModule,
        NzSpinModule,
        NzIconModule,
        NzLayoutModule,
        NzInputModule,
        NzMenuModule,
        NzButtonModule,
        NzModalModule,
        NzFormModule,
        NzRadioModule,
        NzNotificationModule
    ]
})

export class AntdModule {}