my.pkg.MyTextbox = zk.$extends(zul.inp.Textbox, {
	bind_ : function() {
		this.$supers('bind_', arguments);
		console.log(this, 'custom widget bind_()');
	}
}); 
