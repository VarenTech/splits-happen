/**
 * 
 */

const rowLength = 13;

$(document).ready(function() {
	let playerCounter = 1;
	let scoreStyles = {
			textAlign : "center",
			verticalAlign: "middle",
			font: "italic bold 30px Georgia, serif"
	};

	let frameStyles = {
			textAlign : "center",
			verticalAlign: "middle"
	};

	let scoresTable = $('#scoresTable').DataTable({
		'columnDefs': [
			{
				'targets': [1,2,3,4,5,6,7,8,9,10,11],
				'createdCell':  function (td, cellData, rowData, row, col) {
					$(td).attr('contenteditable', 'true'); 
					$(td).css(frameStyles);
				}

			},{
				'targets': [12],
				'createdCell':  function (td, cellData, rowData, row, col) {
					$(td).attr('rowspan', '2');
					$(td).css(scoreStyles);
				}
			}
			],
			"searching": false,
			"paging":false,
			"info":false,
			"ordering": false
	});

	let storeScoreInCell = function(e) {
		setTimeout((function(e){
			let value = e.target.innerText
			value = value.match(/^-.*/) ? value.slice(1):value;
			value = value.slice(0,1).toUpperCase();
			if(this._DT_CellIndex.column < 11 && this._DT_CellIndex.row % 2 === 1 && value.match(/^[Xx]$/)){
				e.target.innerText = "-";
			}
			else if(!value.match(/^[0-9\/\-Xx]$/)){
				e.target.innerText = "-";
			}else{
				scoresTable.cell(e.target._DT_CellIndex.row,e.target._DT_CellIndex.column).data(value);
			}
		}).bind(this,e), 200);
	}

	let runCalulations = function(e) {
		setTimeout((function(e){
			scoresTable.rows('.even').every(function(rowIndex, tableLoop, rowLoop){
				let allCellsFilled = true;
				let oddRow = scoresTable.rows(rowIndex -1).data()[0];
				let evenRow = this.data();
				let firstEmptyCellSet = rowLength - 1;

				let frames = [
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					},
					{
						throw1:0,
						throw2:0,
						finalScore:0
					}
					];



				//Skipping first and last cells, they are not part of the score data
				for(let i=1;i< rowLength - 2;i++){
					let oddCellData=oddRow[i];
					let evenCellData=evenRow[i];
					if(oddCellData.length === 0 || (evenCellData.length === 0 && oddCellData.toUpperCase() !== "X")){
						firstEmptyCellSet = i;
						allCellsFilled = true;
						break;
					}
				}


				let total = 0;
				let prevTHrow = 0;
				let prevPrevThrow = 0;
				let prevRoundWasSpare = false;
				let calculatingStrike = false;
				let calculatingSecondStrike = false;

				let oddCell;
				let evenCell;

				//Skipping first and last cell, they are not part of the score data
				for(let i=1;i<rowLength - 1;i++){
					oddCell=Number(oddRow[i].replace('-',0).replace(/[Xx]/,10));
					evenCell=Number(evenRow[i].replace('/',10-oddCell).replace('',0).replace('-',0).replace(/[Xx]/,10));

					if(oddRow[i].length !== 0){
						frames[i-1].throw1=oddCell;
					}else{
						frames[i-1].throw1=undefined;
					}

					if(evenRow[i].length === 0 && oddCell !== 10){
						frames[i-1].throw2=undefined;
					}else{
						frames[i-1].throw2=evenCell;
					}
				}

				for(let curFrame=0;curFrame < frames.length;curFrame++){
					if(curFrame < frames.length -1 && frames[curFrame].throw2 !== undefined && frames[curFrame].throw1 + frames[curFrame].throw2 < 10){
						frames[curFrame].finalScore = frames[curFrame].throw1 + frames[curFrame].throw2;
					}

					if(curFrame > 0 && curFrame < frames.length -1 && frames[curFrame -1].throw1 &&(frames[curFrame-1].throw1 + frames[curFrame-1].throw2) === 10 && frames[curFrame-1].throw1!== 10){
						frames[curFrame-1].finalScore = 10 + frames[curFrame].throw1; 
					}
					else if(curFrame > 0 && curFrame < frames.length -1 && frames[curFrame-1].throw1=== 10){
						frames[curFrame-1].finalScore = 10 + frames[curFrame].throw1 + frames[curFrame].throw2;
					}

					if(curFrame > 1 && frames[curFrame - 2].throw1 === 10){
						if(frames[curFrame -1].throw1 === 10){	
							frames[curFrame - 2].finalScore = 20 + frames[curFrame].throw1 ; 
						}
						else{
							frames[curFrame - 2].finalScore = 10 + frames[curFrame - 1].throw1 + frames[curFrame - 1].throw2;
						}
					} 
				}

				if(frames[frames.length - 2]){
					if((frames[frames.length - 2].throw1 + frames[frames.length - 2].throw2) === 10 && frames[frames.length - 2].throw1!== 10){
						frames[frames.length - 2].finalScore = 10 + frames[frames.length - 1].throw1;
					}
					else if(frames[frames.length - 2].throw1 === 10){
						frames[frames.length - 2].finalScore = 10 + frames[frames.length - 1].throw1 +frames[frames.length - 1].throw2;
					}
				}



				for(let i=0;i<frames.length;i++){
					total+=Number(frames[i].finalScore);
				}

				scoresTable.cell(rowIndex -1,rowLength-1).data(!isNaN(total) && firstEmptyCellSet > 1 ? total:"");
			});


		}).bind(this,e), 300);
	}

	let getGameJSON = function(){
		let gameArray = [];
		for(let i=1; i< playerCounter; i++){
			gameArray.push(getPlayerJSON(i));
		}
		let gameObject = {
				gameArray : gameArray
		}
		return gameObject;
	}

	let getPlayerJSON = function(playerNumber){
		let finalGameScore = scoresTable.cell(((playerNumber - 1 ) * 2),rowLength-1).data();
		return {
			"finalGameScore" : finalGameScore,
			scoresArray : getFinalFrames(playerNumber)
		}
	}

	let getFinalFrames = function(playerNumber){
		let oddRow = scoresTable.rows((playerNumber - 1 ) * 2).data()[0];
		let evenRow = scoresTable.rows(((playerNumber - 1 ) * 2)+1).data()[0];
		let frames = [];
		for(let i=1;i<rowLength - 1; i++){
			frames.push({
				"throw1" : oddRow[i],
				"throw2" : evenRow[i]
			})
		}
		return frames;
	}

	let addPlayer = function () {
		scoresTable.row.add( [
			'Player '+playerCounter+ ' Throw 1',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-'
			] ).draw( false );
		scoresTable.row.add( [
			'Player '+playerCounter + ' Throw 2',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-',
			'-'
			] ).draw( true );
		playerCounter++;

		$('#scoresTable td').on("keyup", storeScoreInCell);
		$('#scoresTable tr').on("keyup", runCalulations);
	}

	let createTextVersion = function(){
    	// DO POST
		var data = "data=" + JSON.stringify(getGameJSON());
//    	$.ajax({
//			type : "POST",
//			contentType : "application/json",
//			url : "/Bowling/Recorder",
//			data : data,
//			dataType : 'json',
//			success : function(result) {
//				if(result.status == "Done"){
//					$("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + 
//												"Post Successfully! <br>" +
//												"---> Customer's Info: FirstName = " + 
//												result.data.firstname + " ,LastName = " + result.data.lastname + "</p>");
//				}else{
//					$("#postResultDiv").html("<strong>Error</strong>");
//				}
//				console.log(result);
//			},
//			error : function(e) {
//				alert("Error!")
//				console.log("ERROR: ", e);
//			}
//		});
		
		$.post("/Bowling/Recorder", data, function(responseHtml) {
		    $("#download").show(); 
		});
	}

	$('#addPlayer').on( 'click', addPlayer );
	$('#createTextVersion').on( 'click', createTextVersion );
	$('#download').on( 'click', function(){
		$('#download').hide();
	} );

	// Automatically add a first row of data
	$('#addPlayer').click();


} );	