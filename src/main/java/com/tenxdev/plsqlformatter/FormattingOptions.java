/*
   Copyright 2017 Tony BenBrahim

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.tenxdev.plsqlformatter;

public class FormattingOptions {
	public enum WordCase{noChange, uppercase, lowercase, initcaps}; 
	public enum Alignment{compact, fixed, dynamic}; 
	public enum WrapStyle{wrapped, stack, stackOnOverflow};
	public enum WrapStyleEx{wrapped, stack, stackOnOverflow, sync};
	public enum CommaStyle{trailing, leading, leadingWithSpace};
	public enum OperatorArrangement{operatorsLeftCompact, operatorsLeftAlign, operatorsRightCompact, operatorsRightAlign};
	public enum AlignmentSide{left, right};
	
	private int tabSize = 3;
	private int indentSize = 3;
	private int rightMargin=120;
	private boolean sameLineThen=false;
	private boolean sameLineLoop=false;
	private boolean sameLineIs=false;
	private boolean newLineAroundDML=true;
	private boolean newLineAroundType=true;
	private boolean newLineAroundProgramUnitDeclaration=true;
	private boolean newLineAroundProgramUnitBody=true;
	private boolean newLineAroundCursor=true;
	private boolean newLineAroundIfOrCase=true;
	private boolean newLineAroundLoop=true;
	private boolean newLineAroundBlock=true;
	private boolean newLineBeforeLabel=true;
	private int limitConsecutiveNewLines=2;
	private WordCase keywordCase=WordCase.lowercase;
	private WordCase builtInFunctionCase=WordCase.lowercase;
	private Alignment listDeclarationAlignment=Alignment.dynamic;
	private int dynamicAlignmentMinSpacing=3;
	private WrapStyle paramDeclarationWrapping=WrapStyle.stackOnOverflow;
	private Alignment paramDeclarationAlignment=Alignment.dynamic;
	private WrapStyle paramAlignmentWrapping=WrapStyle.stackOnOverflow;
	private Alignment namedParamAlignment=Alignment.dynamic;
	private boolean spaceInsideParentheses=false;
	private boolean spaceOutsideParentheses=true;
	private boolean spaceBetweenConsecutiveParentheses=false;
	private boolean openParenthesisSameLineOnStack=true;
	private boolean closeParenthesisSameLineOnStack=false;
	private CommaStyle listCommanStyleInStack=CommaStyle.trailing;
	private WrapStyle AndOrWrapping=WrapStyle.stackOnOverflow;
	private OperatorArrangement logicalOperatorArrangement=OperatorArrangement.operatorsLeftAlign;
	private  boolean alignLogicalOperatorsWithDDLInStack=true;
	private  boolean alignLogicalOperatorsWhileDDLInStack=true;
	private  boolean alignLogicalOperatorsWithExitInStack=true;
	private WrapStyle arithmeticOperatorWrapStyle=WrapStyle.stackOnOverflow;
	private OperatorArrangement arithmeticOperatorArrangement=OperatorArrangement.operatorsLeftAlign;
	private Alignment assignmentAlignment=Alignment.compact;
	private AlignmentSide DDLKeywordsAlignment=AlignmentSide.right;
	private WrapStyleEx selectIntoWrapping=WrapStyleEx.sync;
	private WrapStyle selectOtherWrapping=WrapStyle.stackOnOverflow;
	private AlignmentSide insertKeywordAlignment=AlignmentSide.right;
	private WrapStyleEx InsertValuesAlignment=WrapStyleEx.sync;
	private WrapStyleEx returnIntoAlignment=WrapStyleEx.sync;
	
	
}
