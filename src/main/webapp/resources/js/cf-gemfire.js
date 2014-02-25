function ApplicationModel() {
	var self = this;

	self.gemfireData = ko.observable(new GemfireModel());
	self.gemfireAdd = ko.observable(new KVModel());

	self.start = function() {
		// Load initial state from server
		$.getJSON("/get", function(data) {
			self.gemfireData().load(data);
		});
	};

	self.refresh = function() {
		// Load initial state from server
		$.getJSON("/get", function(data) {
			self.gemfireData().load(data);
		});
	};
	
	self.add = function() {
		var key = self.gemfireAdd().key();
		var val = self.gemfireAdd().val();
		console.log("Adding key[" + key + "] value[" + val + "]");
		
		$.getJSON("/put/" + key + "/" + val, function(data) {
			self.gemfireAdd().key("Key");
			self.gemfireAdd().val("Value");
			self.refresh();
		});
	};
}

function KVModel() {
	var self = this;
	self.key = ko.observable("Key");
	self.val = ko.observable("Value");
	
};

function GemfireModel() {
	var self = this;
	self.rows = ko.observableArray();
	self.load = function(data) {
		self.rows.removeAll();
		$.each( data, function( key, val ) {
			var row = new Row( key,val );
			self.rows.push( row );
		  });
	};
};

function Row( key, val ) {
	var self = this;
	self.data_key = key;
	self.data_val = val;
	
	self.remove = function(r) {
		console.log("Removing key/value for key: " + r.data_key);
		$.getJSON("/delete/" + r.data_key, function(data) { 	});
	};
};