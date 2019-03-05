$(function() {
	// solving the active menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active'); // this id can be followed from the page
		// controller
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery dataTable

	/*
	 * create a dataSet var products = [
	 *  [ '1', 'ABC' ], [ '2', 'CDE' ], [ '3', 'EFG' ], [ '4', 'GHI' ], [ '5',
	 * 'IJK' ], [ '6', 'KLM' ], [ '7', 'MNO' ], [ '8', 'OPQ' ]
	 *  ];
	 */

	var $table = $('#productListTable'); // # is the id selector

	// execute the below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';  // window.categoryId this is we've added in the listProduct in the script tag so it can call it from there
		
		}

		$table.DataTable({

			lengthMenu : [ [ 3, 5, 10, -1 ],
					[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
			pageLength : 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
				
			},
			columns: [
				{
					data: 'code',
					mRender: function(data,type,row)
					{
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImage"/>';
					}
				},
				
				{
					data: 'name'
					
					
				},
				{
					data: 'brand'
					
				},
				{
					data: 'unitprice',
					mRender: function(data,type,row)
					{
						return '&#8377; ' + data
					}
				},
				{
					data: 'quanitity',
					mRender: function(data,type,row)
					{
						if(data < 1)
							{
								return '<span style="color:red">Out Of Stock!</span>';
								
							}
						return data;
					}
				},
				{
					data: 'id',
					bSortable: false,
					
					mRender: function(data,type,row)
					{
						var str = '';
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class=btn btn-primary""><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						
						if(row.quanitity < 1)
							{
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>'; 
							
							}
						else
							{
							
							str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
					
						
						return str;
					}
				}
				
			]
			
			

		});

	}

});
