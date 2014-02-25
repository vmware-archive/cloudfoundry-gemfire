function ApplicationModel() {
	var self = this;

	self.gemfireData = ko.observable(new GemfireModel());

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
}

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
};