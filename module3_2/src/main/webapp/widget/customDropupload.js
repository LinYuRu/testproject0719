/*
 * afterLoad() makes sure the callback is called after the specified package is loaded to avoid overriding an undefined widget
 */
zk.afterLoad('zkmax.wgt', function() { 
    var oldDropupload = {};
    zk.override(zkmax.wgt.Dropupload.prototype, oldDropupload, {
        _dropAction : function(event) {
    		event.stop();
            // no multidrop allowed before file size check
            if (event.originalEvent.dataTransfer.files.length > 1) {
                jq.alert(msgzk.UPLOAD_ERROR_EXCEED_FILE_COUNT, {icon:'ERROR'});
                return;
            }
            //call the original function (not overridden)
            oldDropupload._dropAction.apply(this, arguments)
        }
    });
});    