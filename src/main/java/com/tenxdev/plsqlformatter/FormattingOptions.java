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

	public int getTabSize() {
		return tabSize;
	}
	public void setTabSize(int tabSize) {
		this.tabSize = tabSize;
	}
	public int getIndentSize() {
		return indentSize;
	}
	public void setIndentSize(int indentSize) {
		this.indentSize = indentSize;
	}
	public int getRightMargin() {
		return rightMargin;
	}
	public void setRightMargin(int rightMargin) {
		this.rightMargin = rightMargin;
	}
	public boolean isSameLineThen() {
		return sameLineThen;
	}
	public void setSameLineThen(boolean sameLineThen) {
		this.sameLineThen = sameLineThen;
	}
	public boolean isSameLineLoop() {
		return sameLineLoop;
	}
	public void setSameLineLoop(boolean sameLineLoop) {
		this.sameLineLoop = sameLineLoop;
	}
	public boolean isSameLineIs() {
		return sameLineIs;
	}
	public void setSameLineIs(boolean sameLineIs) {
		this.sameLineIs = sameLineIs;
	}
	public boolean isNewLineAroundDML() {
		return newLineAroundDML;
	}
	public void setNewLineAroundDML(boolean newLineAroundDML) {
		this.newLineAroundDML = newLineAroundDML;
	}
	public boolean isNewLineAroundType() {
		return newLineAroundType;
	}
	public void setNewLineAroundType(boolean newLineAroundType) {
		this.newLineAroundType = newLineAroundType;
	}
	public boolean isNewLineAroundProgramUnitDeclaration() {
		return newLineAroundProgramUnitDeclaration;
	}
	public void setNewLineAroundProgramUnitDeclaration(boolean newLineAroundProgramUnitDeclaration) {
		this.newLineAroundProgramUnitDeclaration = newLineAroundProgramUnitDeclaration;
	}
	public boolean isNewLineAroundProgramUnitBody() {
		return newLineAroundProgramUnitBody;
	}
	public void setNewLineAroundProgramUnitBody(boolean newLineAroundProgramUnitBody) {
		this.newLineAroundProgramUnitBody = newLineAroundProgramUnitBody;
	}
	public boolean isNewLineAroundCursor() {
		return newLineAroundCursor;
	}
	public void setNewLineAroundCursor(boolean newLineAroundCursor) {
		this.newLineAroundCursor = newLineAroundCursor;
	}
	public boolean isNewLineAroundIfOrCase() {
		return newLineAroundIfOrCase;
	}
	public void setNewLineAroundIfOrCase(boolean newLineAroundIfOrCase) {
		this.newLineAroundIfOrCase = newLineAroundIfOrCase;
	}
	public boolean isNewLineAroundLoop() {
		return newLineAroundLoop;
	}
	public void setNewLineAroundLoop(boolean newLineAroundLoop) {
		this.newLineAroundLoop = newLineAroundLoop;
	}
	public boolean isNewLineAroundBlock() {
		return newLineAroundBlock;
	}
	public void setNewLineAroundBlock(boolean newLineAroundBlock) {
		this.newLineAroundBlock = newLineAroundBlock;
	}
	public boolean isNewLineBeforeLabel() {
		return newLineBeforeLabel;
	}
	public void setNewLineBeforeLabel(boolean newLineBeforeLabel) {
		this.newLineBeforeLabel = newLineBeforeLabel;
	}
	public int getLimitConsecutiveNewLines() {
		return limitConsecutiveNewLines;
	}
	public void setLimitConsecutiveNewLines(int limitConsecutiveNewLines) {
		this.limitConsecutiveNewLines = limitConsecutiveNewLines;
	}
	public WordCase getKeywordCase() {
		return keywordCase;
	}
	public void setKeywordCase(WordCase keywordCase) {
		this.keywordCase = keywordCase;
	}
	public WordCase getBuiltInFunctionCase() {
		return builtInFunctionCase;
	}
	public void setBuiltInFunctionCase(WordCase builtInFunctionCase) {
		this.builtInFunctionCase = builtInFunctionCase;
	}
	public Alignment getListDeclarationAlignment() {
		return listDeclarationAlignment;
	}
	public void setListDeclarationAlignment(Alignment listDeclarationAlignment) {
		this.listDeclarationAlignment = listDeclarationAlignment;
	}
	public int getDynamicAlignmentMinSpacing() {
		return dynamicAlignmentMinSpacing;
	}
	public void setDynamicAlignmentMinSpacing(int dynamicAlignmentMinSpacing) {
		this.dynamicAlignmentMinSpacing = dynamicAlignmentMinSpacing;
	}
	public WrapStyle getParamDeclarationWrapping() {
		return paramDeclarationWrapping;
	}
	public void setParamDeclarationWrapping(WrapStyle paramDeclarationWrapping) {
		this.paramDeclarationWrapping = paramDeclarationWrapping;
	}
	public Alignment getParamDeclarationAlignment() {
		return paramDeclarationAlignment;
	}
	public void setParamDeclarationAlignment(Alignment paramDeclarationAlignment) {
		this.paramDeclarationAlignment = paramDeclarationAlignment;
	}
	public WrapStyle getParamAlignmentWrapping() {
		return paramAlignmentWrapping;
	}
	public void setParamAlignmentWrapping(WrapStyle paramAlignmentWrapping) {
		this.paramAlignmentWrapping = paramAlignmentWrapping;
	}
	public Alignment getNamedParamAlignment() {
		return namedParamAlignment;
	}
	public void setNamedParamAlignment(Alignment namedParamAlignment) {
		this.namedParamAlignment = namedParamAlignment;
	}
	public boolean isSpaceInsideParentheses() {
		return spaceInsideParentheses;
	}
	public void setSpaceInsideParentheses(boolean spaceInsideParentheses) {
		this.spaceInsideParentheses = spaceInsideParentheses;
	}
	public boolean isSpaceOutsideParentheses() {
		return spaceOutsideParentheses;
	}
	public void setSpaceOutsideParentheses(boolean spaceOutsideParentheses) {
		this.spaceOutsideParentheses = spaceOutsideParentheses;
	}
	public boolean isSpaceBetweenConsecutiveParentheses() {
		return spaceBetweenConsecutiveParentheses;
	}
	public void setSpaceBetweenConsecutiveParentheses(boolean spaceBetweenConsecutiveParentheses) {
		this.spaceBetweenConsecutiveParentheses = spaceBetweenConsecutiveParentheses;
	}
	public boolean isOpenParenthesisSameLineOnStack() {
		return openParenthesisSameLineOnStack;
	}
	public void setOpenParenthesisSameLineOnStack(boolean openParenthesisSameLineOnStack) {
		this.openParenthesisSameLineOnStack = openParenthesisSameLineOnStack;
	}
	public boolean isCloseParenthesisSameLineOnStack() {
		return closeParenthesisSameLineOnStack;
	}
	public void setCloseParenthesisSameLineOnStack(boolean closeParenthesisSameLineOnStack) {
		this.closeParenthesisSameLineOnStack = closeParenthesisSameLineOnStack;
	}
	public CommaStyle getListCommanStyleInStack() {
		return listCommanStyleInStack;
	}
	public void setListCommanStyleInStack(CommaStyle listCommanStyleInStack) {
		this.listCommanStyleInStack = listCommanStyleInStack;
	}
	public WrapStyle getAndOrWrapping() {
		return AndOrWrapping;
	}
	public void setAndOrWrapping(WrapStyle andOrWrapping) {
		AndOrWrapping = andOrWrapping;
	}
	public OperatorArrangement getLogicalOperatorArrangement() {
		return logicalOperatorArrangement;
	}
	public void setLogicalOperatorArrangement(OperatorArrangement logicalOperatorArrangement) {
		this.logicalOperatorArrangement = logicalOperatorArrangement;
	}
	public boolean isAlignLogicalOperatorsWithDDLInStack() {
		return alignLogicalOperatorsWithDDLInStack;
	}
	public void setAlignLogicalOperatorsWithDDLInStack(boolean alignLogicalOperatorsWithDDLInStack) {
		this.alignLogicalOperatorsWithDDLInStack = alignLogicalOperatorsWithDDLInStack;
	}
	public boolean isAlignLogicalOperatorsWhileDDLInStack() {
		return alignLogicalOperatorsWhileDDLInStack;
	}
	public void setAlignLogicalOperatorsWhileDDLInStack(boolean alignLogicalOperatorsWhileDDLInStack) {
		this.alignLogicalOperatorsWhileDDLInStack = alignLogicalOperatorsWhileDDLInStack;
	}
	public boolean isAlignLogicalOperatorsWithExitInStack() {
		return alignLogicalOperatorsWithExitInStack;
	}
	public void setAlignLogicalOperatorsWithExitInStack(boolean alignLogicalOperatorsWithExitInStack) {
		this.alignLogicalOperatorsWithExitInStack = alignLogicalOperatorsWithExitInStack;
	}
	public WrapStyle getArithmeticOperatorWrapStyle() {
		return arithmeticOperatorWrapStyle;
	}
	public void setArithmeticOperatorWrapStyle(WrapStyle arithmeticOperatorWrapStyle) {
		this.arithmeticOperatorWrapStyle = arithmeticOperatorWrapStyle;
	}
	public OperatorArrangement getArithmeticOperatorArrangement() {
		return arithmeticOperatorArrangement;
	}
	public void setArithmeticOperatorArrangement(OperatorArrangement arithmeticOperatorArrangement) {
		this.arithmeticOperatorArrangement = arithmeticOperatorArrangement;
	}
	public Alignment getAssignmentAlignment() {
		return assignmentAlignment;
	}
	public void setAssignmentAlignment(Alignment assignmentAlignment) {
		this.assignmentAlignment = assignmentAlignment;
	}
	public AlignmentSide getDDLKeywordsAlignment() {
		return DDLKeywordsAlignment;
	}
	public void setDDLKeywordsAlignment(AlignmentSide dDLKeywordsAlignment) {
		DDLKeywordsAlignment = dDLKeywordsAlignment;
	}
	public WrapStyleEx getSelectIntoWrapping() {
		return selectIntoWrapping;
	}
	public void setSelectIntoWrapping(WrapStyleEx selectIntoWrapping) {
		this.selectIntoWrapping = selectIntoWrapping;
	}
	public WrapStyle getSelectOtherWrapping() {
		return selectOtherWrapping;
	}
	public void setSelectOtherWrapping(WrapStyle selectOtherWrapping) {
		this.selectOtherWrapping = selectOtherWrapping;
	}
	public AlignmentSide getInsertKeywordAlignment() {
		return insertKeywordAlignment;
	}
	public void setInsertKeywordAlignment(AlignmentSide insertKeywordAlignment) {
		this.insertKeywordAlignment = insertKeywordAlignment;
	}
	public WrapStyleEx getInsertValuesAlignment() {
		return InsertValuesAlignment;
	}
	public void setInsertValuesAlignment(WrapStyleEx insertValuesAlignment) {
		InsertValuesAlignment = insertValuesAlignment;
	}
	public WrapStyleEx getReturnIntoAlignment() {
		return returnIntoAlignment;
	}
	public void setReturnIntoAlignment(WrapStyleEx returnIntoAlignment) {
		this.returnIntoAlignment = returnIntoAlignment;
	}
	
}
