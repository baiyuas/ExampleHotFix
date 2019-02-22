package com.baiyu.hotfix;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author baiyu
 */
public class HotFixApplication extends TinkerApplication {

    public HotFixApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.baiyu.hotfix.HotFixApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
